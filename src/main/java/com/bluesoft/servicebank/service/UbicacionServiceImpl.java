package com.bluesoft.servicebank.service;

import com.bluesoft.servicebank.model.dto.CiudadDTO;
import com.bluesoft.servicebank.model.dto.DepartamentoDTO;
import com.bluesoft.servicebank.model.dto.PaisDTO;
import com.bluesoft.servicebank.model.dto.queryparams.GetCiudadParams;
import com.bluesoft.servicebank.model.dto.queryparams.GetDepartamentoParams;
import com.bluesoft.servicebank.model.entity.Ciudad;
import com.bluesoft.servicebank.model.entity.Departamento;
import com.bluesoft.servicebank.model.entity.Pais;
import com.bluesoft.servicebank.repository.ICiudadRepository;
import com.bluesoft.servicebank.repository.IDepartamentoRepository;
import com.bluesoft.servicebank.repository.IPaisRepository;
import com.bluesoft.servicebank.service.mapper.ICiudadMapper;
import com.bluesoft.servicebank.service.mapper.IDepartamentoMapper;
import com.bluesoft.servicebank.service.mapper.IPaisMapper;
import com.bluesoft.servicebank.util.DbUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl implements IUbicacionService {

    private static final String ITEMS = "ITEMS";
    private static final String TOTAL_ITEMS = "TOTAL";

    private final DbUtil dbUtil;

    private final IPaisRepository paisRepository;
    private final IDepartamentoRepository departamentoRepository;
    private final ICiudadRepository ciudadRepository;

    private final IPaisMapper paisMapper;
    private final IDepartamentoMapper departamentoMapper;
    private final ICiudadMapper ciudadMapper;

    @Override
    public List<PaisDTO> listarPaises(Integer idPais, String descPais) {

        PageRequest pageParams = dbUtil.composePageable(0, 250, "descripcion");

        Map<String, Object> map = buscarPaisesPorCriterios(idPais, descPais, pageParams);

        return paisMapper.entityListToDtoList((List<Pais>) map.get(ITEMS));

    }

    private Map<String, Object> buscarPaisesPorCriterios(Integer idPais, String descPais, PageRequest pageable) {

        Page<Pais> page = paisRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!idPais.equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), idPais)));
            }
            if (!StringUtils.isBlank(descPais)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("descripcion"), descPais)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }, pageable);

        Map<String, Object> map = new HashMap<>();

        map.put(ITEMS, page.getContent());
        map.put(TOTAL_ITEMS, page.getTotalElements());

        return map;

    }

    @Override
    public List<DepartamentoDTO> listarDepartamentos(GetDepartamentoParams params) {

        PageRequest pageParams = dbUtil.composePageable(params.getPage(), params.getPageSize(), params.getOrderBy());

        Map<String, Object> map = buscarDepartamentosPorCriterios(params, pageParams);

        return departamentoMapper.entityListToDtoList((List<Departamento>) map.get(ITEMS));
    }

    private Map<String, Object> buscarDepartamentosPorCriterios(GetDepartamentoParams params, PageRequest pageable) {

        Page<Departamento> page = departamentoRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!params.getIdDepto().equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), params.getIdDepto())));
            }
            if (!StringUtils.isBlank(params.getDescDepto())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("descripcion"), params.getDescDepto())));
            }
            if (!params.getIdPais().equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("pais").get("id"), params.getIdPais())));
            }
            if (!StringUtils.isBlank(params.getDescPais())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("pais").get("descripcion"), params.getDescPais())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }, pageable);

        Map<String, Object> map = new HashMap<>();

        map.put(ITEMS, page.getContent());
        map.put(TOTAL_ITEMS, page.getTotalElements());

        return map;

    }

    @Override
    public List<CiudadDTO> listarCiudades(GetCiudadParams params) {

        PageRequest pageParams = dbUtil.composePageable(params.getPage(), params.getPageSize(), params.getOrderBy());

        Map<String, Object> map = buscarCiudadesPorCriterios(params, pageParams);

        return ciudadMapper.entityListToDtoList((List<Ciudad>) map.get(ITEMS));
    }

    private Map<String, Object> buscarCiudadesPorCriterios(GetCiudadParams params, PageRequest pageable) {

        Page<Ciudad> page = ciudadRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!params.getIdCiudad().equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), params.getIdCiudad())));
            }
            if (!StringUtils.isBlank(params.getDescCiudad())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("descripcion"), params.getDescCiudad())));
            }
            if (!params.getIdDepto().equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("departamento").get("id"), params.getIdDepto())));
            }
            if (!StringUtils.isBlank(params.getDescDepto())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("departamento").get("descripcion"), params.getDescDepto())));
            }
            if (!params.getIdPais().equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("departamento").join("pais").get("id"), params.getIdPais())));
            }
            if (!StringUtils.isBlank(params.getDescPais())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("departamento").join("pais").get("descripcion"), params.getDescPais())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }, pageable);

        Map<String, Object> map = new HashMap<>();

        map.put(ITEMS, page.getContent());
        map.put(TOTAL_ITEMS, page.getTotalElements());

        return map;

    }

}
