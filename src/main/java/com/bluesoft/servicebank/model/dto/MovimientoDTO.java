package com.bluesoft.servicebank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MovimientoDTO implements Serializable {

    private Long id;
    private Long idCuenta;
    private Long idTipoMovimiento;
    private String tipoMovimiento;
    private LocalDateTime fechaCreacion;
    private Double valor;
    private String observacion;
    private Long idCiudad;
    private String descCiudad;

}
