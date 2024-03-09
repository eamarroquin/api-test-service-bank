package com.bluesoft.servicebank.controller;

import com.bluesoft.servicebank.model.dto.CuentaDTO;
import com.bluesoft.servicebank.service.cuenta.ICuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banco")
public class BancoController {

    private final ICuentaService cuentaService;

    @GetMapping("cliente/{idCliente}/cuenta")
    public ResponseEntity<List<CuentaDTO>> getCuentasByCliente(@PathVariable Long idCliente) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cuentaService.obtenerCuentasDelCliente(idCliente));
    }

}
