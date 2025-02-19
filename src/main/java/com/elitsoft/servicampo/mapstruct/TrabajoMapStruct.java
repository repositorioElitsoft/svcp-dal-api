package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDto;
import com.elitsoft.servicampo.domain.entity.Trabajo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Trabajo y DTO.
 */
@Mapper(componentModel = "spring")
public interface TrabajoMapStruct {

    /**
     * Convierte un entidad Trabajo a TrabajoDto.
     * @param entity La entidad Trabajo.
     * @return El TrabajoDto.
     */
    TrabajoDto toDto(Trabajo entity);

    /**
     * Convierte un TrabajoDto a entidad Trabajo.
     * @param dto El TrabajoDto.
     * @return La entidad Trabajo.
     */
    Trabajo toEntity(TrabajoDto dto);

    /**
     * Convierte una lista de entidades Trabajo a una lista de TrabajoDtos.
     * @param entities La lista de entidades Trabajo.
     * @return The list of TrabajoDtos.
     */
    List<TrabajoDto> toDtoList(List<Trabajo> entities);

    /**
     * Convierte una lista de TrabajoDtos a una lista de entidades Trabajo entities.
     * @param dtos The list of TrabajoDtos.
     * @return The list of Trabajo entities.
     */
    List<Trabajo> toEntityList(List<TrabajoDto> dtos);
}