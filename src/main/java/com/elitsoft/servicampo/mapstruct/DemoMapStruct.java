package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.DemoDTO;
import com.elitsoft.servicampo.domain.entity.Demo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Demo y DTO.
 */
@Mapper(componentModel = "spring")
public interface DemoMapStruct {

    /**
     * Convierte un entidad Demo a DemoDTO.
     * @param entity La entidad Demo.
     * @return El DemoDTO.
     */
    DemoDTO toDto(Demo entity);

    /**
     * Convierte un DemoDTO a entidad Demo.
     * @param dto El DemoDTO.
     * @return La entidad Demo.
     */
    Demo toEntity(DemoDTO dto);

    /**
     * Convierte una lista de entidades Demo a una lista de DemoDtos.
     * @param entities La lista de entidades Demo.
     * @return The list of DemoDtos.
     */
    List<DemoDTO> toDtoList(List<Demo> entities);

    /**
     * Convierte una lista de DemoDtos a una lista de entidades Demo entities.
     * @param dtos The list of DemoDtos.
     * @return The list of Demo entities.
     */
    List<Demo> toEntityList(List<DemoDTO> dtos);
}