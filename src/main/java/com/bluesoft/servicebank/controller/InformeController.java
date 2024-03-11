package com.bluesoft.servicebank.controller;

import com.bluesoft.servicebank.model.dto.InformeDTO;
import com.bluesoft.servicebank.model.dto.MovimientoDTO;
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

    @GetMapping("/transacciones-retiros")
    public ResponseEntity<List<InformeDTO>> getRetirosCiudad() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(informeService.obtenerRetirosCiudadDiferenteAOrigen());
    }

    @GetMapping("/extracto/cuenta/{idCuenta}")
    public ResponseEntity<List<MovimientoDTO>> getExtractoCuenta(@PathVariable Long idCuenta,
                                                                 @RequestParam int mes,
                                                                 @RequestParam int year) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(informeService.obtenerExtractosCuenta(idCuenta, mes, year));
    }

}
