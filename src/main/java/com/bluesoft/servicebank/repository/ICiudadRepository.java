package com.bluesoft.servicebank.repository;

import com.bluesoft.servicebank.model.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad, Long>, JpaSpecificationExecutor<Ciudad> {
}
