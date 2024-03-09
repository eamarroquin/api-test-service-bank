package com.bluesoft.servicebank.service.cuenta;

import com.bluesoft.servicebank.exception.NotFoundException;
import com.bluesoft.servicebank.model.dto.CuentaDTO;
import com.bluesoft.servicebank.model.entity.*;
import com.bluesoft.servicebank.repository.ICiudadRepository;
import com.bluesoft.servicebank.repository.IClienteRepository;
import com.bluesoft.servicebank.repository.ICuentaRepository;
import com.bluesoft.servicebank.repository.ITipoCuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements ICuentaService {

    private final EntityManager entityManager;

    private final ICuentaRepository cuentaRepository;

    private final ITipoCuentaRepository tipoCuentaRepository;

    private final IClienteRepository clienteRepository;

    private final ICiudadRepository ciudadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CuentaDTO> obtenerCuentasDelCliente(Long idCliente) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CuentaDTO> query = cb.createQuery(CuentaDTO.class);
        Root<Cuenta> rootCuenta = query.from(Cuenta.class);
        Join<Cuenta, Cliente> joinCliente = rootCuenta.join("cliente", JoinType.INNER);
        Join<Cuenta, TipoCuenta> joinTipo = rootCuenta.join("tipoCuenta", JoinType.INNER);
        Join<Cuenta, Ciudad> joinCiudad = rootCuenta.join("ciudad", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.and(cb.equal(joinCliente.get("id"), idCliente)));

        query.select(cb.construct(CuentaDTO.class,
                        rootCuenta.get("id"),
                        rootCuenta.get("numeroCuenta"),
                        joinTipo.get("id").alias("idTipoCuenta"),
                        joinTipo.get("descripcion").alias("tipoCuenta"),
                        rootCuenta.get("saldo"),
                        joinCliente.get("id").alias("idCliente"),
                        joinCiudad.get("id").alias("idCiudad"),
                        joinCiudad.get("descripcion").alias("descCiudad")))
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.asc(rootCuenta.get("numeroCuenta")));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public CuentaDTO registrarCuenta(Long idCliente, CuentaDTO cuentaDTO) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new NotFoundException("No existe cliente con ID: " + idCliente));

        TipoCuenta tipoCuenta = tipoCuentaRepository.findById(cuentaDTO.getIdTipoCuenta())
                .orElseThrow(() -> new NotFoundException("No existe el tipo de cuenta: " + cuentaDTO.getIdTipoCuenta()));

        Ciudad ciudad = ciudadRepository.findById(cuentaDTO.getIdCiudad())
                .orElseThrow(() -> new NotFoundException("No existe la ciudad con identificador: " + cuentaDTO.getIdCiudad()));

        Cuenta cuenta = cuentaRepository.save(Cuenta.builder()
                .numeroCuenta(UUID.randomUUID().toString())
                .tipoCuenta(tipoCuenta)
                .saldo(cuentaDTO.getSaldo())
                .cliente(cliente)
                .ciudad(ciudad)
                .build());

        return mapCuentaToDTO(cuenta);

    }

    private CuentaDTO mapCuentaToDTO(Cuenta cuenta) {

        return CuentaDTO.builder()
                .id(cuenta.getId())
                .numeroCuenta(cuenta.getNumeroCuenta())
                .idTipoCuenta(cuenta.getTipoCuenta().getId())
                .tipoCuenta(cuenta.getTipoCuenta().getDescripcion())
                .saldo(cuenta.getSaldo())
                .idCliente(cuenta.getCliente().getId())
                .idCiudad(cuenta.getCiudad().getId())
                .descCiudad(cuenta.getCiudad().getDescripcion())
                .build();
    }


}
