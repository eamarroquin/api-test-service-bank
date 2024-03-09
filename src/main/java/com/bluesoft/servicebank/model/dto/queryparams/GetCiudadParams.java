package com.bluesoft.servicebank.model.dto.queryparams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetCiudadParams {

    private Integer idCiudad;
    private String descCiudad;
    private Integer idDepto;
    private String descDepto;
    private Integer idPais;
    private String descPais;
    private Integer page;
    private Integer pageSize;
    private String orderBy;

}
