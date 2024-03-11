package com.bluesoft.servicebank.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformeDTO implements Serializable {

    private Long id;
    private String dni;
    private String nombre;
    private Long cantidadTransacciones;
    private Double valorTransacciones;

}
