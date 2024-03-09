package com.bluesoft.servicebank.controller;

import com.bluesoft.servicebank.model.dto.TipoCuentaDTO;
import com.bluesoft.servicebank.model.dto.TipoMovimientoDTO;
import com.bluesoft.servicebank.service.general.ITipoCuentaService;
import com.bluesoft.servicebank.service.general.ITipoMovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class GeneralController {

    private final ITipoCuentaService tipoCuentaService;
    private final ITipoMovimientoService tipoMovimientoService;

    @GetMapping("tipo-de-cuenta")
    public ResponseEntity<List<TipoCuentaDTO>> getTiposCuenta(@RequestParam Optional<Integer> idTipo,
                                                              @RequestParam Optional<String> descTipo) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tipoCuentaService
                        .listarTiposDeCuenta(idTipo.orElse(-1), descTipo.orElse("")));
    }

    @GetMapping("tipo-de-movimiento")
    public ResponseEntity<List<TipoMovimientoDTO>> getTiposMovimiento(@RequestParam Optional<Integer> idTipo,
                                                                      @RequestParam Optional<String> descTipo) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tipoMovimientoService
                        .listarTiposDeMovimiento(idTipo.orElse(-1), descTipo.orElse("")));
    }

}
