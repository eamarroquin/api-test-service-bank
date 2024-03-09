package com.bluesoft.servicebank.service.cuenta;

import com.bluesoft.servicebank.model.dto.CuentaDTO;

import java.util.List;

public interface ICuentaService {

    List<CuentaDTO> obtenerCuentasDelCliente(Long idCliente);

    CuentaDTO registrarCuenta(Long idCliente, CuentaDTO cuentaDTO);

}
