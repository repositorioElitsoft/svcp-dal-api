package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDTO;
import com.elitsoft.servicampo.domain.entity.ClasificacionCliente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad ClasificacionCliente y DTO.
 */
@Mapper(componentModel = "spring")
public interface ClasificacionClienteMapStruct {

    /**
     * Convierte un entidad ClasificacionCliente a ClasificacionClienteDTO.
     * @param entity La entidad ClasificacionCliente.
     * @return El ClasificacionClienteDTO.
     */
    ClasificacionClienteDTO toDto(ClasificacionCliente entity);

    /**
     * Convierte un ClasificacionClienteDTO a entidad ClasificacionCliente.
     * @param dto El ClasificacionClienteDTO.
     * @return La entidad ClasificacionCliente.
     */
    ClasificacionCliente toEntity(ClasificacionClienteDTO dto);

    /**
     * Convierte una lista de entidades ClasificacionCliente a una lista de ClasificacionClienteDtos.
     * @param entities La lista de entidades ClasificacionCliente.
     * @return The list of ClasificacionClienteDtos.
     */
    List<ClasificacionClienteDTO> toDtoList(List<ClasificacionCliente> entities);

    /**
     * Convierte una lista de ClasificacionClienteDtos a una lista de entidades ClasificacionCliente entities.
     * @param dtos The list of ClasificacionClienteDtos.
     * @return The list of ClasificacionCliente entities.
     */
    List<ClasificacionCliente> toEntityList(List<ClasificacionClienteDTO> dtos);
}