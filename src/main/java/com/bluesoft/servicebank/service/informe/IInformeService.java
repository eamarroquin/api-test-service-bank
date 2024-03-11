package com.bluesoft.servicebank.service.informe;

import com.bluesoft.servicebank.model.dto.InformeDTO;

import java.util.List;

public interface IInformeService {

    List<InformeDTO> obtenerTransaccionesPorCliente(int mes, int year);

}
