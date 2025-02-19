package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.domain.entity.TipoEmpleado;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoEmpleado y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoEmpleadoMapStruct {

    /**
     * Convierte un entidad TipoEmpleado a TipoEmpleadoDto.
     * @param entity La entidad TipoEmpleado.
     * @return El TipoEmpleadoDto.
     */
    TipoEmpleadoDto toDto(TipoEmpleado entity);

    /**
     * Convierte un TipoEmpleadoDto a entidad TipoEmpleado.
     * @param dto El TipoEmpleadoDto.
     * @return La entidad TipoEmpleado.
     */
    TipoEmpleado toEntity(TipoEmpleadoDto dto);

    /**
     * Convierte una lista de entidades TipoEmpleado a una lista de TipoEmpleadoDtos.
     * @param entities La lista de entidades TipoEmpleado.
     * @return The list of TipoEmpleadoDtos.
     */
    List<TipoEmpleadoDto> toDtoList(List<TipoEmpleado> entities);

    /**
     * Convierte una lista de TipoEmpleadoDtos a una lista de entidades TipoEmpleado entities.
     * @param dtos The list of TipoEmpleadoDtos.
     * @return The list of TipoEmpleado entities.
     */
    List<TipoEmpleado> toEntityList(List<TipoEmpleadoDto> dtos);
}