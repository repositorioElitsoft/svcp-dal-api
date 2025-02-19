package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EstadoDto;
import com.elitsoft.servicampo.domain.entity.Estado;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Estado y DTO.
 */
@Mapper(componentModel = "spring")
public interface EstadoMapStruct {

    /**
     * Convierte un entidad Estado a EstadoDto.
     * @param entity La entidad Estado.
     * @return El EstadoDto.
     */
    EstadoDto toDto(Estado entity);

    /**
     * Convierte un EstadoDto a entidad Estado.
     * @param dto El EstadoDto.
     * @return La entidad Estado.
     */
    Estado toEntity(EstadoDto dto);

    /**
     * Convierte una lista de entidades Estado a una lista de EstadoDtos.
     * @param entities La lista de entidades Estado.
     * @return The list of EstadoDtos.
     */
    List<EstadoDto> toDtoList(List<Estado> entities);

    /**
     * Convierte una lista de EstadoDtos a una lista de entidades Estado entities.
     * @param dtos The list of EstadoDtos.
     * @return The list of Estado entities.
     */
    List<Estado> toEntityList(List<EstadoDto> dtos);
}