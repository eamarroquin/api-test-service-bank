package com.bluesoft.servicebank.service.ubicacion;

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
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl implements IUbicacionService {

    private final IPaisRepository paisRepository;
    private final IDepartamentoRepository departamentoRepository;
    private final ICiudadRepository ciudadRepository;

    private final IPaisMapper paisMapper;
    private final IDepartamentoMapper departamentoMapper;
    private final ICiudadMapper ciudadMapper;

    @Override
    public List<PaisDTO> listarPaises(Integer idPais, String descPais) {

        return paisMapper.entityListToDtoList(buscarPaisesPorCriterios(idPais, descPais));

    }

    private List<Pais> buscarPaisesPorCriterios(Integer idPais, String descPais) {

        return paisRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!idPais.equals(-1)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), idPais)));
            }
            if (!StringUtils.isBlank(descPais)) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("descripcion"), descPais)));
            }

            query.orderBy(criteriaBuilder.asc(root.get("descripcion")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });

    }

    @Override
    public List<DepartamentoDTO> listarDepartamentos(GetDepartamentoParams params) {

        return departamentoMapper.entityListToDtoList(buscarDepartamentosPorCriterios(params));
    }

    private List<Departamento> buscarDepartamentosPorCriterios(GetDepartamentoParams params) {

        return departamentoRepository.findAll((root, query, criteriaBuilder) -> {
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

            query.orderBy(criteriaBuilder.asc(root.get("descripcion")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });

    }

    @Override
    public List<CiudadDTO> listarCiudades(GetCiudadParams params) {

        return ciudadMapper.entityListToDtoList(buscarCiudadesPorCriterios(params));
    }

    private List<Ciudad> buscarCiudadesPorCriterios(GetCiudadParams params) {

        return ciudadRepository.findAll((root, query, criteriaBuilder) -> {
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

            query.orderBy(criteriaBuilder.asc(root.get("descripcion")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });

    }

}
