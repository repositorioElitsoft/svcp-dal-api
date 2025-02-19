package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EstructuraFormularioDto;
import com.elitsoft.servicampo.domain.entity.EstructuraFormulario;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad EstructuraFormulario y DTO.
 */
@Mapper(componentModel = "spring")
public interface EstructuraFormularioMapStruct {

    /**
     * Convierte un entidad EstructuraFormulario a EstructuraFormularioDto.
     * @param entity La entidad EstructuraFormulario.
     * @return El EstructuraFormularioDto.
     */
    EstructuraFormularioDto toDto(EstructuraFormulario entity);

    /**
     * Convierte un EstructuraFormularioDto a entidad EstructuraFormulario.
     * @param dto El EstructuraFormularioDto.
     * @return La entidad EstructuraFormulario.
     */
    EstructuraFormulario toEntity(EstructuraFormularioDto dto);

    /**
     * Convierte una lista de entidades EstructuraFormulario a una lista de EstructuraFormularioDtos.
     * @param entities La lista de entidades EstructuraFormulario.
     * @return The list of EstructuraFormularioDtos.
     */
    List<EstructuraFormularioDto> toDtoList(List<EstructuraFormulario> entities);

    /**
     * Convierte una lista de EstructuraFormularioDtos a una lista de entidades EstructuraFormulario entities.
     * @param dtos The list of EstructuraFormularioDtos.
     * @return The list of EstructuraFormulario entities.
     */
    List<EstructuraFormulario> toEntityList(List<EstructuraFormularioDto> dtos);
}