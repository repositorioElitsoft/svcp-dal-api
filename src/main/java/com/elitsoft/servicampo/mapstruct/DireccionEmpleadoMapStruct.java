package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.DireccionEmpleadoDTO;
import com.elitsoft.servicampo.domain.entity.DireccionEmpleado;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad DireccionEmpleado y DTO.
 */
@Mapper(componentModel = "spring")
public interface DireccionEmpleadoMapStruct {

    /**
     * Convierte un entidad DireccionEmpleado a DireccionEmpleadoDTO.
     * @param entity La entidad DireccionEmpleado.
     * @return El DireccionEmpleadoDTO.
     */
    DireccionEmpleadoDTO toDTO(DireccionEmpleado entity);

    /**
     * Convierte un DireccionEmpleadoDTO a entidad DireccionEmpleado.
     * @param dto El DireccionEmpleadoDTO.
     * @return La entidad DireccionEmpleado.
     */
    DireccionEmpleado toEntity(DireccionEmpleadoDTO dto);

    /**
     * Convierte una lista de entidades DireccionEmpleado a una lista de DireccionEmpleadoDTOs.
     * @param entities La lista de entidades DireccionEmpleado.
     * @return The list of DireccionEmpleadoDTOs.
     */
    List<DireccionEmpleadoDTO> toDTOList(List<DireccionEmpleado> entities);

    /**
     * Convierte una lista de DireccionEmpleadoDTOs a una lista de entidades DireccionEmpleado entities.
     * @param dto The list of DireccionEmpleadoDTOs.
     * @return The list of DireccionEmpleado entities.
     */
    List<DireccionEmpleado> toEntityList(List<DireccionEmpleadoDTO> dto);
}