package com.bluesoft.servicebank.repository;

import com.bluesoft.servicebank.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {
}
