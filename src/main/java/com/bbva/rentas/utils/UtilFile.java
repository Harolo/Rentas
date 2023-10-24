package com.bbva.rentas.utils;

import com.bbva.rentas.dto.SolicitudRentaRequestDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UtilFile {

    public void unzipFile(String zipFilePath, String destinationPath) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String entryName = zipEntry.getName();
                String filePath = destinationPath + File.separator + entryName;

                if (!zipEntry.isDirectory()) {
                    new File(filePath).getParentFile().mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(filePath)) {
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }

                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }

    public static SolicitudRentaRequestDto dataConstruction(String[] datos) {
        SolicitudRentaRequestDto solicitudRentaRequestDto = new SolicitudRentaRequestDto();
        solicitudRentaRequestDto.setTipoIdentificacion(cleanData(datos[0]));
        solicitudRentaRequestDto.setTipoDocumento(cleanData(datos[1]));
        solicitudRentaRequestDto.setTipoPrimerApellido(cleanData(datos[2]));
        solicitudRentaRequestDto.setTipoSegundoApellido(cleanData(datos[3]));
        solicitudRentaRequestDto.setTipoPrimerNombre(cleanData(datos[4]));
        solicitudRentaRequestDto.setTipoSegundoNombre(cleanData(datos[5]));
        solicitudRentaRequestDto.setTipoGenero(cleanData(datos[6]));
        solicitudRentaRequestDto.setTipoFechaNacimiento(cleanData(datos[7]));
        solicitudRentaRequestDto.setTipoEstadoExpedicion(cleanData(datos[8]));
        solicitudRentaRequestDto.setTipoEstadoActual(cleanData(datos[9]));
        solicitudRentaRequestDto.setTipoNumeroPoliza(cleanData(datos[10]));
        solicitudRentaRequestDto.setTipoFechaExpedicion(cleanData(datos[11]));
        solicitudRentaRequestDto.setTipoFechaDerecho(cleanData(datos[12]));
        solicitudRentaRequestDto.setTipoInteresTecnico(cleanData(datos[13]));
        solicitudRentaRequestDto.setTipoOrigenPrestacion(cleanData(datos[14]));
        solicitudRentaRequestDto.setTipoModalidadPrestacion(cleanData(datos[15]));
        solicitudRentaRequestDto.setTipoFechaInicioRenDif(cleanData(datos[16]));
        solicitudRentaRequestDto.setTipoAfpVen(cleanData(datos[17]));
        solicitudRentaRequestDto.setTipoValorMesada(cleanData(datos[18]));
        solicitudRentaRequestDto.setTipoNumeroMesadas(cleanData(datos[19]));
        solicitudRentaRequestDto.setTipoValorReservaInVe(cleanData(datos[20]));
        solicitudRentaRequestDto.setTipoValorReservaSob(cleanData(datos[21]));
        solicitudRentaRequestDto.setTipoValorReservaAux(cleanData(datos[22]));
        solicitudRentaRequestDto.setTipoValorOtrReserva(cleanData(datos[23]));
        solicitudRentaRequestDto.setTipoGastosAdmon(cleanData(datos[24]));
        solicitudRentaRequestDto.setTipoValorTotRes(cleanData(datos[25]));
        solicitudRentaRequestDto.setDocSoporteJudicial(cleanData(datos[26]));
        solicitudRentaRequestDto.setFechaDocSopteJud(cleanData(datos[27]));
        solicitudRentaRequestDto.setObservacionesFalloJud(cleanData(datos[28]));
        solicitudRentaRequestDto.setTipoTipoBeneficiario(cleanData(datos[29]));
        solicitudRentaRequestDto.setTipoTipoDocumento(cleanData(datos[30]));
        solicitudRentaRequestDto.setTipoDocumentoBeneficiario(cleanData(datos[31]));
        solicitudRentaRequestDto.setTipoPrimerApellidoBeneficiario(cleanData(datos[32]));
        solicitudRentaRequestDto.setTipoSegundoApellidoBeneficiario(cleanData(datos[33]));
        solicitudRentaRequestDto.setTipoPrimerNombreBeneficiario(cleanData(datos[34]));
        solicitudRentaRequestDto.setTipoSegundoNombreBeneficiario(cleanData(datos[35]));
        solicitudRentaRequestDto.setTipoGeneroBeneficiario(cleanData(datos[36]));
        solicitudRentaRequestDto.setTipoFechaNacimientoBenef(cleanData(datos[37]));
        solicitudRentaRequestDto.setTipoIndEstudiaAct(cleanData(datos[38]));
        solicitudRentaRequestDto.setTipoIndDependeEco(cleanData(datos[39]));
        solicitudRentaRequestDto.setTipoEstadoExpedicionBeneficiario(cleanData(datos[40]));
        solicitudRentaRequestDto.setTipoEstadoActualBeneficiario(cleanData(datos[41]));
        solicitudRentaRequestDto.setTemporBeneficiario("V");
        return solicitudRentaRequestDto;
    }

    public static String cleanData(String data) {
        return data != null ? data.replace(" ", "") : data;
    }

    public static String formatDateGeneric() {
        TimeZone tz = TimeZone.getTimeZone("GMT-5");
        Calendar c = Calendar.getInstance(tz);

        return String.format("%02d", c.get(Calendar.DAY_OF_MONTH)) +
                String.format("%02d", (c.get(Calendar.MONTH) + 1)) +
                String.format("%02d", c.get(Calendar.YEAR));
    }

}
