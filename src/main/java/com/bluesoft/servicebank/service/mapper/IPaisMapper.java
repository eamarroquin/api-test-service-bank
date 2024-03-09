package com.bluesoft.servicebank.service.mapper;

import com.bluesoft.servicebank.model.dto.PaisDTO;
import com.bluesoft.servicebank.model.entity.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IPaisMapper {

    PaisDTO entityToDto(Pais entity);

    List<PaisDTO> entityListToDtoList(List<Pais> entityList);

}
