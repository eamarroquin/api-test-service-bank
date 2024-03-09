package com.bluesoft.servicebank.service.general;

import com.bluesoft.servicebank.model.dto.TipoCuentaDTO;

import java.util.List;

public interface ITipoCuentaService {

    List<TipoCuentaDTO> listarTiposDeCuenta(Integer idTipo, String descTipo);

}
