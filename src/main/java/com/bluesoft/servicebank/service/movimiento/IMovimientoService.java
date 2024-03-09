package com.bluesoft.servicebank.service.movimiento;

import com.bluesoft.servicebank.model.dto.MovimientoDTO;

import java.util.List;

public interface IMovimientoService {

    List<MovimientoDTO> obtenerMovimientosRecientes(Long idCuenta);

    MovimientoDTO registrarMovimiento(Long idCuenta, MovimientoDTO movimientoDTO);

}
