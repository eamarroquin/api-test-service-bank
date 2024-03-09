package com.bluesoft.servicebank.repository;

import com.bluesoft.servicebank.model.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoMovimientoRepository extends JpaRepository<TipoMovimiento, Long>, JpaSpecificationExecutor<TipoMovimiento> {
}
