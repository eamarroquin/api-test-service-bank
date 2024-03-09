package com.bluesoft.servicebank.service;

import com.bluesoft.servicebank.model.dto.CiudadDTO;
import com.bluesoft.servicebank.model.dto.DepartamentoDTO;
import com.bluesoft.servicebank.model.dto.PaisDTO;
import com.bluesoft.servicebank.model.dto.queryparams.GetCiudadParams;
import com.bluesoft.servicebank.model.dto.queryparams.GetDepartamentoParams;

import java.util.List;

public interface IUbicacionService {

    List<PaisDTO> listarPaises(Integer idPais, String descPais);

    List<DepartamentoDTO> listarDepartamentos(GetDepartamentoParams params);

    List<CiudadDTO> listarCiudades(GetCiudadParams params);

}
