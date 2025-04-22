package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDTO;
import com.elitsoft.servicampo.domain.entity.TipoEmpleado;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoEmpleado y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoEmpleadoMapStruct {

    /**
     * Convierte un entidad TipoEmpleado a TipoEmpleadoDTO.
     * @param entity La entidad TipoEmpleado.
     * @return El TipoEmpleadoDTO.
     */
    TipoEmpleadoDTO toDTO(TipoEmpleado entity);

    /**
     * Convierte un TipoEmpleadoDTO a entidad TipoEmpleado.
     * @param dto El TipoEmpleadoDTO.
     * @return La entidad TipoEmpleado.
     */
    TipoEmpleado toEntity(TipoEmpleadoDTO dto);

    /**
     * Convierte una lista de entidades TipoEmpleado a una lista de TipoEmpleadoDtos.
     * @param entities La lista de entidades TipoEmpleado.
     * @return The list of TipoEmpleadoDtos.
     */
    List<TipoEmpleadoDTO> toDTOList(List<TipoEmpleado> entities);

    /**
     * Convierte una lista de TipoEmpleadoDtos a una lista de entidades TipoEmpleado entities.
     * @param dtos The list of TipoEmpleadoDtos.
     * @return The list of TipoEmpleado entities.
     */
    List<TipoEmpleado> toEntityList(List<TipoEmpleadoDTO> dtos);
}