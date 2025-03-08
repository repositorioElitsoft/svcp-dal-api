package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ComunaDTO;
import com.elitsoft.servicampo.domain.entity.Comuna;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Comuna y DTO.
 */
@Mapper(componentModel = "spring")
public interface ComunaMapStruct {

    /**
     * Convierte un entidad Comuna a ComunaDTO.
     * @param entity La entidad Comuna.
     * @return El ComunaDTO.
     */
    ComunaDTO toDto(Comuna entity);

    /**
     * Convierte un ComunaDTO a entidad Comuna.
     * @param dto El ComunaDTO.
     * @return La entidad Comuna.
     */
    Comuna toEntity(ComunaDTO dto);

    /**
     * Convierte una lista de entidades Comuna a una lista de ComunaDtos.
     * @param entities La lista de entidades Comuna.
     * @return The list of ComunaDtos.
     */
    List<ComunaDTO> toDtoList(List<Comuna> entities);

    /**
     * Convierte una lista de ComunaDtos a una lista de entidades Comuna entities.
     * @param dtos The list of ComunaDtos.
     * @return The list of Comuna entities.
     */
    List<Comuna> toEntityList(List<ComunaDTO> dtos);
}