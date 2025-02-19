package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EmpleadoDto;
import com.elitsoft.servicampo.domain.entity.Empleado;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Empleado y DTO.
 */
@Mapper(componentModel = "spring")
public interface EmpleadoMapStruct {

    /**
     * Convierte un entidad Empleado a EmpleadoDto.
     * @param entity La entidad Empleado.
     * @return El EmpleadoDto.
     */
    EmpleadoDto toDto(Empleado entity);

    /**
     * Convierte un EmpleadoDto a entidad Empleado.
     * @param dto El EmpleadoDto.
     * @return La entidad Empleado.
     */
    Empleado toEntity(EmpleadoDto dto);

    /**
     * Convierte una lista de entidades Empleado a una lista de EmpleadoDtos.
     * @param entities La lista de entidades Empleado.
     * @return The list of EmpleadoDtos.
     */
    List<EmpleadoDto> toDtoList(List<Empleado> entities);

    /**
     * Convierte una lista de EmpleadoDtos a una lista de entidades Empleado entities.
     * @param dtos The list of EmpleadoDtos.
     * @return The list of Empleado entities.
     */
    List<Empleado> toEntityList(List<EmpleadoDto> dtos);
}