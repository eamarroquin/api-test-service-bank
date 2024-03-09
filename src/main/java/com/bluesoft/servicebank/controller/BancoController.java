package com.bluesoft.servicebank.controller;

import com.bluesoft.servicebank.model.dto.CuentaDTO;
import com.bluesoft.servicebank.model.dto.MovimientoDTO;
import com.bluesoft.servicebank.service.cuenta.ICuentaService;
import com.bluesoft.servicebank.service.movimiento.IMovimientoService;
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

    private final IMovimientoService movimientoService;

    @GetMapping("cliente/{idCliente}/cuenta")
    public ResponseEntity<List<CuentaDTO>> getCuentasByCliente(@PathVariable Long idCliente) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cuentaService.obtenerCuentasDelCliente(idCliente));
    }

    @GetMapping("cuenta/{idCuenta}/movimientos")
    public ResponseEntity<List<MovimientoDTO>> getMovimientosByCuenta(@PathVariable Long idCuenta) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movimientoService.obtenerMovimientosRecientes(idCuenta));
    }

    @PostMapping("/cuenta/{idCuenta}/movimiento")
    public ResponseEntity<MovimientoDTO> postMovimiento(@PathVariable Long idCuenta,
                                                        @RequestBody MovimientoDTO movimientoDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movimientoService.registrarMovimiento(idCuenta, movimientoDTO));
    }

}
