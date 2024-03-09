package com.bluesoft.servicebank.service.ubicacion;

import com.bluesoft.servicebank.model.dto.CiudadDTO;
import com.bluesoft.servicebank.model.dto.DepartamentoDTO;
import com.bluesoft.servicebank.model.dto.PaisDTO;
import com.bluesoft.servicebank.model.dto.params.GetCiudadParams;
import com.bluesoft.servicebank.model.dto.params.GetDepartamentoParams;

import java.util.List;

public interface IUbicacionService {

    List<PaisDTO> listarPaises(Integer idPais, String descPais);

    List<DepartamentoDTO> listarDepartamentos(GetDepartamentoParams params);

    List<CiudadDTO> listarCiudades(GetCiudadParams params);

}
