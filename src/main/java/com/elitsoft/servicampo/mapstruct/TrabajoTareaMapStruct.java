package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TrabajoTareaDto;
import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TrabajoTarea y DTO.
 */
@Mapper(componentModel = "spring")
public interface TrabajoTareaMapStruct {

    /**
     * Convierte un entidad TrabajoTarea a TrabajoTareaDto.
     * @param entity La entidad TrabajoTarea.
     * @return El TrabajoTareaDto.
     */
    TrabajoTareaDto toDto(TrabajoTarea entity);

    /**
     * Convierte un TrabajoTareaDto a entidad TrabajoTarea.
     * @param dto El TrabajoTareaDto.
     * @return La entidad TrabajoTarea.
     */
    TrabajoTarea toEntity(TrabajoTareaDto dto);

    /**
     * Convierte una lista de entidades TrabajoTarea a una lista de TrabajoTareaDtos.
     * @param entities La lista de entidades TrabajoTarea.
     * @return The list of TrabajoTareaDtos.
     */
    List<TrabajoTareaDto> toDtoList(List<TrabajoTarea> entities);

    /**
     * Convierte una lista de TrabajoTareaDtos a una lista de entidades TrabajoTarea entities.
     * @param dtos The list of TrabajoTareaDtos.
     * @return The list of TrabajoTarea entities.
     */
    List<TrabajoTarea> toEntityList(List<TrabajoTareaDto> dtos);
}