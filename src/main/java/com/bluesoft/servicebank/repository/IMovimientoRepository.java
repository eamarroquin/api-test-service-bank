package com.bluesoft.servicebank.repository;

import com.bluesoft.servicebank.model.dto.InformeDTO;
import com.bluesoft.servicebank.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT new com.bluesoft.servicebank.model.dto.InformeDTO(m.cuenta.cliente.id, m.cuenta.cliente.dni, m.cuenta.cliente.nombre, COUNT(m) AS totalTransacciones) " +
            "FROM Movimiento m " +
            "WHERE MONTH(m.fechaCreacion) = :mes AND YEAR(m.fechaCreacion) = :year "  +
            "GROUP BY m.cuenta.cliente.id " +
            "ORDER BY totalTransacciones DESC")
    List<InformeDTO> contarTransaccionesPorCuenta(@Param("mes") int mes, @Param("year") int year);

}
