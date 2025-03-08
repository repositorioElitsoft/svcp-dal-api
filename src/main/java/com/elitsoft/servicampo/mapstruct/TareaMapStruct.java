package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TareaDTO;
import com.elitsoft.servicampo.domain.entity.Tarea;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Tarea y DTO.
 */
@Mapper(componentModel = "spring")
public interface TareaMapStruct {

    /**
     * Convierte un entidad Tarea a TareaDTO.
     * @param entity La entidad Tarea.
     * @return El TareaDTO.
     */
    TareaDTO toDto(Tarea entity);

    /**
     * Convierte un TareaDTO a entidad Tarea.
     * @param dto El TareaDTO.
     * @return La entidad Tarea.
     */
    Tarea toEntity(TareaDTO dto);

    /**
     * Convierte una lista de entidades Tarea a una lista de TareaDtos.
     * @param entities La lista de entidades Tarea.
     * @return The list of TareaDtos.
     */
    List<TareaDTO> toDtoList(List<Tarea> entities);

    /**
     * Convierte una lista de TareaDtos a una lista de entidades Tarea entities.
     * @param dtos The list of TareaDtos.
     * @return The list of Tarea entities.
     */
    List<Tarea> toEntityList(List<TareaDTO> dtos);
}