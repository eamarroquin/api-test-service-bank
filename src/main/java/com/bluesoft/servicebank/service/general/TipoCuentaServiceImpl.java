package com.bluesoft.servicebank.service.general;

import com.bluesoft.servicebank.model.dto.TipoCuentaDTO;
import com.bluesoft.servicebank.model.entity.TipoCuenta;
import com.bluesoft.servicebank.repository.ITipoCuentaRepository;
import com.bluesoft.servicebank.service.mapper.ITipoCuentaMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoCuentaServiceImpl implements ITipoCuentaService {

    private final ITipoCuentaRepository tipoCuentaRepository;

    private final ITipoCuentaMapper tipoCuentaMapper;

    @Override
    public List<TipoCuentaDTO> listarTiposDeCuenta(Integer idTipo, String descTipo) {

        List<TipoCuenta> tipoCuentaList = tipoCuentaRepository.findAll((root, query, criteriaBuilder) -> {
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

        return tipoCuentaMapper.entityListToDtoList(tipoCuentaList);

    }

}
