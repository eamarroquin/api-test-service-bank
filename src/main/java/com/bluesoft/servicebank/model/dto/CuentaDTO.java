package com.bluesoft.servicebank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CuentaDTO implements Serializable {

    private Long id;
    private String numeroCuenta;
    private Long idTipoCuenta;
    private String tipoCuenta;
    private Double saldo;
    private Long idCliente;
    private Long idCiudad;
    private String descCiudad;

}
