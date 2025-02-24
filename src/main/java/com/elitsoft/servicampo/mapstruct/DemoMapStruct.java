package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.DemoDto;
import com.elitsoft.servicampo.domain.entity.Demo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Demo y DTO.
 */
@Mapper(componentModel = "spring")
public interface DemoMapStruct {

    /**
     * Convierte un entidad Demo a DemoDto.
     * @param entity La entidad Demo.
     * @return El DemoDto.
     */
    DemoDto toDto(Demo entity);

    /**
     * Convierte un DemoDto a entidad Demo.
     * @param dto El DemoDto.
     * @return La entidad Demo.
     */
    Demo toEntity(DemoDto dto);

    /**
     * Convierte una lista de entidades Demo a una lista de DemoDtos.
     * @param entities La lista de entidades Demo.
     * @return The list of DemoDtos.
     */
    List<DemoDto> toDtoList(List<Demo> entities);

    /**
     * Convierte una lista de DemoDtos a una lista de entidades Demo entities.
     * @param dtos The list of DemoDtos.
     * @return The list of Demo entities.
     */
    List<Demo> toEntityList(List<DemoDto> dtos);
}