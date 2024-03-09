package com.bluesoft.servicebank.service.mapper;

import com.bluesoft.servicebank.model.dto.TipoMovimientoDTO;
import com.bluesoft.servicebank.model.entity.TipoMovimiento;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ITipoMovimientoMapper {

    TipoMovimientoDTO entityToDto(TipoMovimiento entity);

    List<TipoMovimientoDTO> entityListToDtoList(List<TipoMovimiento> entityList);
}
