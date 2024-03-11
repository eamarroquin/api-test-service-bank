package com.bluesoft.servicebank.service.informe;

import com.bluesoft.servicebank.model.dto.InformeDTO;
import com.bluesoft.servicebank.model.dto.MovimientoDTO;
import com.bluesoft.servicebank.repository.IMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InformeServiceImpl implements IInformeService {

    private final IMovimientoRepository  movimientoRepository;

    @Override
    public List<InformeDTO> obtenerTransaccionesPorCliente(int mes, int year) {

        return movimientoRepository.contarTransaccionesPorCuenta(mes, year);

    }

    @Override
    public List<InformeDTO> obtenerRetirosCiudadDiferenteAOrigen() {

        return movimientoRepository.obtenerClientesRetiros();

    }

    @Override
    public List<MovimientoDTO> obtenerExtractosCuenta(Long idCuenta, int mes, int year) {
        return movimientoRepository.consultarExtractoMensual(idCuenta, mes, year);
    }

}
