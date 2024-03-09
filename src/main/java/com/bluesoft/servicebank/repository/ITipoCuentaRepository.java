package com.bluesoft.servicebank.repository;

import com.bluesoft.servicebank.model.entity.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoCuentaRepository extends JpaRepository<TipoCuenta, Long>, JpaSpecificationExecutor<TipoCuenta> {
}
