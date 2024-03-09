package com.bluesoft.servicebank.service.cuenta;

import com.bluesoft.servicebank.model.dto.CuentaDTO;
import com.bluesoft.servicebank.model.entity.Ciudad;
import com.bluesoft.servicebank.model.entity.Cliente;
import com.bluesoft.servicebank.model.entity.Cuenta;
import com.bluesoft.servicebank.model.entity.TipoCuenta;
import com.bluesoft.servicebank.repository.ICuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements ICuentaService {

    private final EntityManager entityManager;

    private final ICuentaRepository cuentaRepository;

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

}
