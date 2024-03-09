package com.bluesoft.servicebank.service.mapper;

import com.bluesoft.servicebank.model.dto.TipoCuentaDTO;
import com.bluesoft.servicebank.model.entity.TipoCuenta;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ITipoCuentaMapper {

    TipoCuentaDTO entityToDto(TipoCuenta entity);

    List<TipoCuentaDTO> entityListToDtoList(List<TipoCuenta> entityList);

}
