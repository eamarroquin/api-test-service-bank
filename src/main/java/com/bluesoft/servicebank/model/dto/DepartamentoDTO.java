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
public class DepartamentoDTO implements Serializable {

    private Long id;
    private String descripcion;

}
