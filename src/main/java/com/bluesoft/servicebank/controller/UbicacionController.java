package com.bluesoft.servicebank.controller;

import com.bluesoft.servicebank.model.dto.CiudadDTO;
import com.bluesoft.servicebank.model.dto.DepartamentoDTO;
import com.bluesoft.servicebank.model.dto.PaisDTO;
import com.bluesoft.servicebank.model.dto.queryparams.GetCiudadParams;
import com.bluesoft.servicebank.model.dto.queryparams.GetDepartamentoParams;
import com.bluesoft.servicebank.service.ubicacion.IUbicacionService;
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
public class UbicacionController {

    private final IUbicacionService ubicacionService;

    @GetMapping("pais")
    public ResponseEntity<List<PaisDTO>> getPaises(@RequestParam Optional<Integer> idPais,
                                                   @RequestParam Optional<String> descPais) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ubicacionService.listarPaises(idPais.orElse(-1), descPais.orElse("")));
    }

    @GetMapping("departamento")
    public ResponseEntity<List<DepartamentoDTO>> getDepartamentos(@RequestParam Optional<Integer> idDepto,
                                                                  @RequestParam Optional<String> descDepto,
                                                                  @RequestParam Optional<Integer> idPais,
                                                                  @RequestParam Optional<String> descPais) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ubicacionService.listarDepartamentos(GetDepartamentoParams.builder()
                        .idDepto(idDepto.orElse(-1))
                        .descDepto(descDepto.orElse(""))
                        .idPais(idPais.orElse(-1))
                        .descPais(descPais.orElse(""))
                        .build()));
    }

    @GetMapping("ciudad")
    public ResponseEntity<List<CiudadDTO>> getCiudades(@RequestParam Optional<Integer> idCiudad,
                                                       @RequestParam Optional<String> descCiudad,
                                                       @RequestParam Optional<Integer> idDepto,
                                                       @RequestParam Optional<String> descDepto,
                                                       @RequestParam Optional<Integer> idPais,
                                                       @RequestParam Optional<String> descPais) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ubicacionService.listarCiudades(GetCiudadParams.builder()
                        .idCiudad(idCiudad.orElse(-1))
                        .descCiudad(descCiudad.orElse(""))
                        .idDepto(idDepto.orElse(-1))
                        .descDepto(descDepto.orElse(""))
                        .idPais(idPais.orElse(-1))
                        .descPais(descPais.orElse(""))
                        .build()));
    }

}
