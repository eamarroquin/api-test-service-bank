package com.bluesoft.servicebank.service.general;

import com.bluesoft.servicebank.model.dto.TipoMovimientoDTO;
import com.bluesoft.servicebank.model.entity.TipoMovimiento;
import com.bluesoft.servicebank.repository.ITipoMovimientoRepository;
import com.bluesoft.servicebank.service.mapper.ITipoMovimientoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoMovimientoImpl implements ITipoMovimientoService {

    private final ITipoMovimientoRepository tipoMovimientoRepository;

    private final ITipoMovimientoMapper tipoMovimientoMapper;

    @Override
    public List<TipoMovimientoDTO> listarTiposDeMovimiento(Integer idTipo, String descTipo) {

        List<TipoMovimiento> tipoMovimientoList = tipoMovimientoRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!idTipo.equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), idTipo)));
            }
            if (!StringUtils.isBlank(descTipo)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("descripcion"), descTipo)));
            }

            query.orderBy(criteriaBuilder.asc(root.get("descripcion")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });

        return tipoMovimientoMapper.entityListToDtoList(tipoMovimientoList);

    }

}
