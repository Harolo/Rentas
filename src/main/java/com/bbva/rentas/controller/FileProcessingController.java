package com.bbva.rentas.controller;

import com.bbva.rentas.dto.SolicitudRentaRequestDto;
import com.bbva.rentas.service.FileProcessingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class FileProcessingController {

    private final FileProcessingService fileProcessingService;

    @PostMapping("/proceso")
    public ResponseEntity<List<SolicitudRentaRequestDto>> procressFile(){

        List<SolicitudRentaRequestDto> list = fileProcessingService.processFile();
        return ResponseEntity.ok(list);
    }

}
