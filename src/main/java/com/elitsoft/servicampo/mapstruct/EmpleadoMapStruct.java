package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EmpleadoDTO;
import com.elitsoft.servicampo.domain.entity.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Empleado y DTO.
 */
@Mapper(componentModel = "spring")
public interface EmpleadoMapStruct {

    /**
     * Convierte un entidad Empleado a EmpleadoDTO.
     * @param entity La entidad Empleado.
     * @return El EmpleadoDTO.
     */
    @Mapping(target = "contrasena", ignore = true)
    EmpleadoDTO toDTO(Empleado entity);

    /**
     * Convierte un EmpleadoDTO a entidad Empleado.
     * @param dto El EmpleadoDTO.
     * @return La entidad Empleado.
     */
    Empleado toEntity(EmpleadoDTO dto);

    /**
     * Convierte una lista de entidades Empleado a una lista de EmpleadoDTOs.
     * @param entities La lista de entidades Empleado.
     * @return The list of EmpleadoDTOs.
     */
    @Mapping(target = "contrasena", ignore = true)
    List<EmpleadoDTO> toDTOList(List<Empleado> entities);

    /**
     * Convierte una lista de EmpleadoDTOs a una lista de entidades Empleado entities.
     * @param dto The list of EmpleadoDTOs.
     * @return The list of Empleado entities.
     */
    List<Empleado> toEntityList(List<EmpleadoDTO> dto);
}