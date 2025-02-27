package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.SegmentacionClienteDto;
import com.elitsoft.servicampo.domain.entity.SegmentacionCliente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad SegmentacionCliente y DTO.
 */
@Mapper(componentModel = "spring")
public interface SegmentacionClienteMapStruct {

    /**
     * Convierte un entidad SegmentacionCliente a SegmentacionClienteDto.
     * @param entity La entidad SegmentacionCliente.
     * @return El SegmentacionClienteDto.
     */
    SegmentacionClienteDto toDto(SegmentacionCliente entity);

    /**
     * Convierte un SegmentacionClienteDto a entidad SegmentacionCliente.
     * @param dto El SegmentacionClienteDto.
     * @return La entidad SegmentacionCliente.
     */
    SegmentacionCliente toEntity(SegmentacionClienteDto dto);

    /**
     * Convierte una lista de entidades SegmentacionCliente a una lista de SegmentacionClienteDtos.
     * @param entities La lista de entidades SegmentacionCliente.
     * @return The list of SegmentacionClienteDtos.
     */
    List<SegmentacionClienteDto> toDtoList(List<SegmentacionCliente> entities);

    /**
     * Convierte una lista de SegmentacionClienteDtos a una lista de entidades SegmentacionCliente entities.
     * @param dtos The list of SegmentacionClienteDtos.
     * @return The list of SegmentacionCliente entities.
     */
    List<SegmentacionCliente> toEntityList(List<SegmentacionClienteDto> dtos);
}