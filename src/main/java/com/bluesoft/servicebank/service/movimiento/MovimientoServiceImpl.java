package com.bluesoft.servicebank.service.movimiento;

import com.bluesoft.servicebank.exception.BadRequestException;
import com.bluesoft.servicebank.exception.ConflictException;
import com.bluesoft.servicebank.exception.NotFoundException;
import com.bluesoft.servicebank.model.dto.MovimientoDTO;
import com.bluesoft.servicebank.model.entity.Ciudad;
import com.bluesoft.servicebank.model.entity.Cuenta;
import com.bluesoft.servicebank.model.entity.Movimiento;
import com.bluesoft.servicebank.model.entity.TipoMovimiento;
import com.bluesoft.servicebank.repository.ICiudadRepository;
import com.bluesoft.servicebank.repository.ICuentaRepository;
import com.bluesoft.servicebank.repository.IMovimientoRepository;
import com.bluesoft.servicebank.repository.ITipoMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements IMovimientoService {

    private final EntityManager entityManager;

    private final IMovimientoRepository movimientoRepository;

    private final ICuentaRepository cuentaRepository;

    private final ITipoMovimientoRepository tipoMovimientoRepository;

    private final ICiudadRepository ciudadRepository;

    @Override
    public List<MovimientoDTO> obtenerMovimientosRecientes(Long idCuenta) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovimientoDTO> query = cb.createQuery(MovimientoDTO.class);
        Root<Movimiento> root = query.from(Movimiento.class);
        Join<MovimientoDTO, Cuenta> joinCuenta = root.join("cuenta", JoinType.INNER);
        Join<MovimientoDTO, TipoMovimiento> joinTipo = root.join("tipoMovimiento", JoinType.INNER);
        Join<MovimientoDTO, Ciudad> joinCiudad = root.join("ciudad", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.and(cb.equal(joinCuenta.get("id"), idCuenta)));

        query.select(cb.construct(MovimientoDTO.class,
                        root.get("id"),
                        joinCuenta.get("id").alias("idCuenta"),
                        joinTipo.get("id").alias("idTipoMovimiento"),
                        joinTipo.get("descripcion").alias("tipoMovimiento"),
                        root.get("fechaCreacion"),
                        root.get("valor"),
                        root.get("observacion"),
                        joinCiudad.get("id").alias("idCiudad"),
                        joinCiudad.get("descripcion").alias("descCiudad")))
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.desc(root.get("fechaCreacion")));

        return entityManager.createQuery(query).setMaxResults(5).getResultList();

    }

    @Override
    public MovimientoDTO registrarMovimiento(Long idCuenta, MovimientoDTO movimientoDTO) {

        Cuenta cuenta = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new NotFoundException("No existe la cuenta con ID: " + idCuenta));

        TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findById(movimientoDTO.getIdTipoMovimiento())
                .orElseThrow(() -> new NotFoundException("No existe el tipo de movimiento: " + movimientoDTO.getIdTipoMovimiento()));

        Ciudad ciudad = ciudadRepository.findById(movimientoDTO.getIdCiudad())
                .orElseThrow(() -> new NotFoundException("No existe la ciudad con identificador: " + movimientoDTO.getIdCiudad()));

        if (tipoMovimiento.getDescripcion().equals("RETIRO")) {
            validarSaldoSuficiente(cuenta.getSaldo(), movimientoDTO.getValor());
        }

        Movimiento movimiento = movimientoRepository.save(Movimiento.builder()
                .cuenta(cuenta)
                .tipoMovimiento(tipoMovimiento)
                .fechaCreacion(LocalDateTime.now())
                .valor(movimientoDTO.getValor())
                .observacion(movimientoDTO.getObservacion())
                .ciudad(ciudad)
                .build());

        actualizarSaldoPostTransaccion(tipoMovimiento, cuenta, movimiento.getValor());

        return mapMovimientoToDTO(movimiento);
    }

    private void validarSaldoSuficiente(Double saldo, Double valorTransaccion) {
        if (saldo < valorTransaccion) {
            throw new BadRequestException("No se puede completar la transacción debido a saldo insuficiente en la cuenta. Por favor, asegúrese de tener fondos suficientes antes de intentar nuevamente");
        }
    }

    private void actualizarSaldoPostTransaccion(TipoMovimiento tipoMovimiento, Cuenta cuenta, Double valorTransaccion) {

        Double nuevoSaldo = 0.00;

        if (tipoMovimiento.getDescripcion().equals("CONSIGNACIÓN")) {
            nuevoSaldo = cuenta.getSaldo() + valorTransaccion;
        } else if (tipoMovimiento.getDescripcion().equals("RETIRO")) {
            nuevoSaldo = cuenta.getSaldo() - valorTransaccion;
        }else {
            throw new ConflictException("Error al procesar el tipo de movimiento. Verifique la información proporcionada e inténtelo nuevamente.");
        }

        cuenta.setSaldo(nuevoSaldo);

        cuentaRepository.save(cuenta);

    }

    private MovimientoDTO mapMovimientoToDTO(Movimiento movimiento) {
        return MovimientoDTO.builder()
                .id(movimiento.getId())
                .idCuenta(movimiento.getCuenta().getId())
                .idTipoMovimiento(movimiento.getTipoMovimiento().getId())
                .tipoMovimiento(movimiento.getTipoMovimiento().getDescripcion())
                .fechaCreacion(movimiento.getFechaCreacion())
                .valor(movimiento.getValor())
                .observacion(movimiento.getObservacion())
                .idCiudad(movimiento.getCiudad().getId())
                .descCiudad(movimiento.getCiudad().getDescripcion())
                .build();
    }

}
