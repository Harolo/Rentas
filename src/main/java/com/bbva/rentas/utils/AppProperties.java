package com.bbva.rentas.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "file:/de/app/rentas.properties")
@Getter
public class AppProperties {

    @Value("${filePath}")
    private String filePath;

    @Value("${filePoliciesPath}")
    private String filePoliciesPath;

    @Value("${uri.service2}")
    private String service2;

    @Value("${rutaOrigen.zip}")
    private String rutaOrigenZip;

    @Value("${rutaDestino.zip}")
    private String rutaDestinoZip;

    @Value("${bufferSize.zip}")
    private int bufferSizeZip = 4096;

}
