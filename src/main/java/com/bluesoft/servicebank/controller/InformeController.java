package com.bluesoft.servicebank.controller;

import com.bluesoft.servicebank.model.dto.InformeDTO;
import com.bluesoft.servicebank.service.informe.IInformeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banco/informes")
public class InformeController {

    private final IInformeService informeService;

    @GetMapping("/transacciones-por-cliente")
    public ResponseEntity<List<InformeDTO>> getTransaccionesCliente(@RequestParam int mes,
                                                                    @RequestParam int year) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(informeService.obtenerTransaccionesPorCliente(mes, year));
    }

}
