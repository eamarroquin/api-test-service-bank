package com.bluesoft.servicebank.service.mapper;

import com.bluesoft.servicebank.model.dto.DepartamentoDTO;
import com.bluesoft.servicebank.model.entity.Departamento;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IDepartamentoMapper {

    DepartamentoDTO entityToDto(Departamento entity);

    List<DepartamentoDTO> entityListToDtoList(List<Departamento> entityList);

}
