package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDTO;
import com.elitsoft.servicampo.domain.entity.Trabajo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Trabajo y DTO.
 */
@Mapper(componentModel = "spring")
public interface TrabajoMapStruct {

    /**
     * Convierte un entidad Trabajo a TrabajoDTO.
     * @param entity La entidad Trabajo.
     * @return El TrabajoDTO.
     */
    TrabajoDTO toDTO(Trabajo entity);

    /**
     * Convierte un TrabajoDTO a entidad Trabajo.
     * @param dto El TrabajoDTO.
     * @return La entidad Trabajo.
     */
    Trabajo toEntity(TrabajoDTO dto);

    /**
     * Convierte una lista de entidades Trabajo a una lista de TrabajoDtos.
     * @param entities La lista de entidades Trabajo.
     * @return The list of TrabajoDtos.
     */
    List<TrabajoDTO> toDTOList(List<Trabajo> entities);

    /**
     * Convierte una lista de TrabajoDtos a una lista de entidades Trabajo entities.
     * @param dtos The list of TrabajoDtos.
     * @return The list of Trabajo entities.
     */
    List<Trabajo> toEntityList(List<TrabajoDTO> dtos);
}