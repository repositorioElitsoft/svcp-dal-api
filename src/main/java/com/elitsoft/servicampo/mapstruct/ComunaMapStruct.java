package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ComunaDto;
import com.elitsoft.servicampo.domain.entity.Comuna;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Comuna y DTO.
 */
@Mapper(componentModel = "spring")
public interface ComunaMapStruct {

    /**
     * Convierte un entidad Comuna a ComunaDto.
     * @param entity La entidad Comuna.
     * @return El ComunaDto.
     */
    ComunaDto toDto(Comuna entity);

    /**
     * Convierte un ComunaDto a entidad Comuna.
     * @param dto El ComunaDto.
     * @return La entidad Comuna.
     */
    Comuna toEntity(ComunaDto dto);

    /**
     * Convierte una lista de entidades Comuna a una lista de ComunaDtos.
     * @param entities La lista de entidades Comuna.
     * @return The list of ComunaDtos.
     */
    List<ComunaDto> toDtoList(List<Comuna> entities);

    /**
     * Convierte una lista de ComunaDtos a una lista de entidades Comuna entities.
     * @param dtos The list of ComunaDtos.
     * @return The list of Comuna entities.
     */
    List<Comuna> toEntityList(List<ComunaDto> dtos);
}