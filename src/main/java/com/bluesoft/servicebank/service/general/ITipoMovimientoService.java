package com.bluesoft.servicebank.service.general;

import com.bluesoft.servicebank.model.dto.TipoMovimientoDTO;

import java.util.List;

public interface ITipoMovimientoService {

    List<TipoMovimientoDTO> listarTiposDeMovimiento(Integer idTipo, String descTipo);

}
