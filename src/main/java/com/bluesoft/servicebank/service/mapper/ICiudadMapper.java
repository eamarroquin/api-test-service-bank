package com.bluesoft.servicebank.service.mapper;

import com.bluesoft.servicebank.model.dto.CiudadDTO;
import com.bluesoft.servicebank.model.entity.Ciudad;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ICiudadMapper {

    CiudadDTO entityToDto(Ciudad entity);

    List<CiudadDTO> entityListToDtoList(List<Ciudad> entityList);

}
