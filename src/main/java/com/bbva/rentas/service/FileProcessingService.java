package com.bbva.rentas.service;

import com.bbva.rentas.dto.RegistroSolicitudRentaElement;
import com.bbva.rentas.dto.SolicitudRentaRequestDto;
import com.bbva.rentas.dto.SolicitudRentaResponse;
import com.bbva.rentas.utils.AppProperties;
import com.bbva.rentas.utils.UtilFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


@Service
public class FileProcessingService {

    @Autowired
    private AppProperties appProperties;


    public List<SolicitudRentaRequestDto> processFile() {
        descomprimirArchivoZip();
        List<SolicitudRentaRequestDto> dataOutList = new ArrayList<>();
        String timeFormat = UtilFile.formatDateGeneric();
        String formatNameFile = "SOLICITUD_RENTAS_OBP_" + timeFormat;
        try {
            File carpeta = new File(appProperties.getFilePath());
            File[] archivos = carpeta.listFiles();

            assert archivos != null;
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().contains(formatNameFile)) {
                    FileReader fileReader = new FileReader(archivo);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String linea;
                    while ((linea = bufferedReader.readLine()) != null) {

                        String[] datos = linea.split(";", -1);

                        SolicitudRentaRequestDto solicitudRentaRequestDto = UtilFile.dataConstruction(datos);

                        if ("S".equals(solicitudRentaRequestDto.getDocSoporteJudicial())) {
                            File pdfFile = getPdfByTypeDocument(solicitudRentaRequestDto.getTipoDocumento());
                            if (pdfFile != null) {
                                String base64Pdf = encodeFileToBase64(pdfFile);
                                solicitudRentaRequestDto.setDocSoporteJudicial(base64Pdf);
                            }
                        }
                        SolicitudRentaResponse solicitudRentaResponse = enviarAServicioExterno(solicitudRentaRequestDto);
                        solicitudRentaRequestDto.setCodigoTransaccion(solicitudRentaResponse.getTipoCodigoTransaccion().getValue());
                        dataOutList.add(solicitudRentaRequestDto);
                    }
                    bufferedReader.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataOutList;
    }

    private File getPdfByTypeDocument(String tipoDocumento) {
        File dir = new File(appProperties.getFilePoliciesPath());
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains(tipoDocumento) && file.getName().endsWith(".pdf")) {
                    return file;
                } else {
                    System.out.println(">>> No se encontro el archivo con el documento" + tipoDocumento);
                }
            }
        }
        return null;
    }

    private String encodeFileToBase64(File file) throws IOException {
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        return Base64.encodeBase64String(fileBytes);
    }

    public SolicitudRentaResponse enviarAServicioExterno(SolicitudRentaRequestDto solicitudRentaRequestDto) {
        RegistroSolicitudRentaElement registroElement = new RegistroSolicitudRentaElement();
        registroElement.mapSolicitudRentaRequestDto(solicitudRentaRequestDto);
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(appProperties.getService2());

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(registroElement);

            StringEntity entity = new StringEntity(jsonRequest);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity responseEntity = response.getEntity();
                String jsonResponse = EntityUtils.toString(responseEntity);

                return objectMapper.readValue(jsonResponse, SolicitudRentaResponse.class);
            } else {
                System.err.println("Error en la llamada al servicio externo: " + response.getStatusLine().getStatusCode());
                throw new ConnectionClosedException("Error en la llamada al servicio externo: " + response.getStatusLine().getStatusCode());            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar la llamada al servicio externo "+ e.getMessage());
            return null;
        }
    }




    public void descomprimirArchivoZip() {
        File archivoZipOrigen = new File(appProperties.getRutaOrigenZip());
        if (!archivoZipOrigen.exists()) {
            System.err.println("El archivo ZIP de origen no existe en la ruta especificada.");
            return; // Salir de la función si el archivo no existe
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(appProperties.getRutaOrigenZip())))) {
            byte[] buffer = new byte[appProperties.getBufferSizeZip()];
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String nombreArchivo = new File(zipEntry.getName()).getName();
                File archivoDescomprimido = new File(appProperties.getRutaDestinoZip() + nombreArchivo);
                archivoDescomprimido.getParentFile().mkdirs();

                try (FileOutputStream fos = new FileOutputStream(archivoDescomprimido)) {
                    int bytesRead;
                    while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
                zipEntry = zipInputStream.getNextEntry();
            }

            System.out.println("Archivos descomprimidos con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al descomprimir el archivo ZIP: " + e.getMessage());
        }
    }
}



