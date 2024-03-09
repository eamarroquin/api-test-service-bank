package com.bluesoft.servicebank.repository;

import com.bluesoft.servicebank.model.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, Long>, JpaSpecificationExecutor<Departamento> {
}
