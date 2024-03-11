package com.bluesoft.servicebank.service.informe;

import com.bluesoft.servicebank.model.dto.InformeDTO;
import com.bluesoft.servicebank.model.dto.MovimientoDTO;

import java.util.List;

public interface IInformeService {

    List<InformeDTO> obtenerTransaccionesPorCliente(int mes, int year);

    List<InformeDTO> obtenerRetirosCiudadDiferenteAOrigen();

    List<MovimientoDTO> obtenerExtractosCuenta(Long idCuenta, int mes, int year);

}
