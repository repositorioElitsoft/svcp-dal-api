package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TrabajoTareaDTO;
import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TrabajoTarea y DTO.
 */
@Mapper(componentModel = "spring")
public interface TrabajoTareaMapStruct {

    /**
     * Convierte un entidad TrabajoTarea a TrabajoTareaDTO.
     * @param entity La entidad TrabajoTarea.
     * @return El TrabajoTareaDTO.
     */
    TrabajoTareaDTO toDTO(TrabajoTarea entity);

    /**
     * Convierte un TrabajoTareaDTO a entidad TrabajoTarea.
     * @param dto El TrabajoTareaDTO.
     * @return La entidad TrabajoTarea.
     */
    TrabajoTarea toEntity(TrabajoTareaDTO dto);

    /**
     * Convierte una lista de entidades TrabajoTarea a una lista de TrabajoTareaDtos.
     * @param entities La lista de entidades TrabajoTarea.
     * @return The list of TrabajoTareaDtos.
     */
    List<TrabajoTareaDTO> toDTOList(List<TrabajoTarea> entities);

    /**
     * Convierte una lista de TrabajoTareaDtos a una lista de entidades TrabajoTarea entities.
     * @param dtos The list of TrabajoTareaDtos.
     * @return The list of TrabajoTarea entities.
     */
    List<TrabajoTarea> toEntityList(List<TrabajoTareaDTO> dtos);
}