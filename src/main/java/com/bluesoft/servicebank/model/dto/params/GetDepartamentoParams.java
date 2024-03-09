package com.bluesoft.servicebank.model.dto.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetDepartamentoParams {

    private Integer idDepto;
    private String descDepto;
    private Integer idPais;
    private String descPais;

}
