package com.bluesoft.servicebank.service.mapper;

import com.bluesoft.servicebank.model.dto.ClienteDTO;
import com.bluesoft.servicebank.model.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IClienteMapper {

    @Mapping(target = "password", ignore = true)
    ClienteDTO entityToDto(Cliente entity);

    Cliente dtoToEntity(ClienteDTO dto);

}
