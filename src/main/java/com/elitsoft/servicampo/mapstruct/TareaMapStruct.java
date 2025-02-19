package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TareaDto;
import com.elitsoft.servicampo.domain.entity.Tarea;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Tarea y DTO.
 */
@Mapper(componentModel = "spring")
public interface TareaMapStruct {

    /**
     * Convierte un entidad Tarea a TareaDto.
     * @param entity La entidad Tarea.
     * @return El TareaDto.
     */
    TareaDto toDto(Tarea entity);

    /**
     * Convierte un TareaDto a entidad Tarea.
     * @param dto El TareaDto.
     * @return La entidad Tarea.
     */
    Tarea toEntity(TareaDto dto);

    /**
     * Convierte una lista de entidades Tarea a una lista de TareaDtos.
     * @param entities La lista de entidades Tarea.
     * @return The list of TareaDtos.
     */
    List<TareaDto> toDtoList(List<Tarea> entities);

    /**
     * Convierte una lista de TareaDtos a una lista de entidades Tarea entities.
     * @param dtos The list of TareaDtos.
     * @return The list of Tarea entities.
     */
    List<Tarea> toEntityList(List<TareaDto> dtos);
}