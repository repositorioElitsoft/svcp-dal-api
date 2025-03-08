package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EstructuraFormularioDTO;
import com.elitsoft.servicampo.domain.entity.EstructuraFormulario;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad EstructuraFormulario y DTO.
 */
@Mapper(componentModel = "spring")
public interface EstructuraFormularioMapStruct {

    /**
     * Convierte un entidad EstructuraFormulario a EstructuraFormularioDTO.
     * @param entity La entidad EstructuraFormulario.
     * @return El EstructuraFormularioDTO.
     */
    EstructuraFormularioDTO toDto(EstructuraFormulario entity);

    /**
     * Convierte un EstructuraFormularioDTO a entidad EstructuraFormulario.
     * @param dto El EstructuraFormularioDTO.
     * @return La entidad EstructuraFormulario.
     */
    EstructuraFormulario toEntity(EstructuraFormularioDTO dto);

    /**
     * Convierte una lista de entidades EstructuraFormulario a una lista de EstructuraFormularioDtos.
     * @param entities La lista de entidades EstructuraFormulario.
     * @return The list of EstructuraFormularioDtos.
     */
    List<EstructuraFormularioDTO> toDtoList(List<EstructuraFormulario> entities);

    /**
     * Convierte una lista de EstructuraFormularioDtos a una lista de entidades EstructuraFormulario entities.
     * @param dtos The list of EstructuraFormularioDtos.
     * @return The list of EstructuraFormulario entities.
     */
    List<EstructuraFormulario> toEntityList(List<EstructuraFormularioDTO> dtos);
}