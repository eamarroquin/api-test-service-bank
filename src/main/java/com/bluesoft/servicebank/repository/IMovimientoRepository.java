package com.bluesoft.servicebank.repository;

import com.bluesoft.servicebank.model.dto.InformeDTO;
import com.bluesoft.servicebank.model.dto.MovimientoDTO;
import com.bluesoft.servicebank.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {

     @Query("SELECT new com.bluesoft.servicebank.model.dto.MovimientoDTO(m.id, m.cuenta.id, m.tipoMovimiento.id, m.tipoMovimiento.descripcion, m.fechaCreacion, m.valor, m.observacion, m.ciudad.id, m.ciudad.descripcion) " +
             "FROM Movimiento m " +
             "WHERE m.cuenta.id = :idCuenta AND MONTH(m.fechaCreacion) = :mes AND YEAR(m.fechaCreacion) = :year " +
             "ORDER BY m.fechaCreacion")
    List<MovimientoDTO> consultarExtractoMensual(@Param("idCuenta") Long idCuenta, @Param("mes") int mes, @Param("year") int year);

    @Query("SELECT new com.bluesoft.servicebank.model.dto.InformeDTO(m.cuenta.cliente.id, m.cuenta.cliente.dni, m.cuenta.cliente.nombre, COUNT(m) AS cantidadTransacciones, SUM(m.valor) AS valorTransacciones) " +
            "FROM Movimiento m " +
            "WHERE MONTH(m.fechaCreacion) = :mes AND YEAR(m.fechaCreacion) = :year "  +
            "GROUP BY m.cuenta.cliente.id " +
            "ORDER BY cantidadTransacciones DESC")
    List<InformeDTO> contarTransaccionesPorCuenta(@Param("mes") int mes, @Param("year") int year);

    @Query("SELECT new com.bluesoft.servicebank.model.dto.InformeDTO(m.cuenta.cliente.id, m.cuenta.cliente.dni, m.cuenta.cliente.nombre, COUNT(m.id) as cantidadTransacciones, SUM(CASE WHEN m.valor > 1000000 THEN m.valor ELSE 0 END) as valorTransacciones) " +
            "FROM Movimiento m " +
            "WHERE m.tipoMovimiento.descripcion = 'RETIRO' AND m.cuenta.ciudad.id <> m.ciudad.id AND m.valor > 1000000 " +
            "GROUP BY m.cuenta.cliente.id " +
            "HAVING SUM(m.valor) > 1000000")
    List<InformeDTO> obtenerClientesRetiros();

}
