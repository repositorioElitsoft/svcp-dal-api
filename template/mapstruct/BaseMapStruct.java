package com.elitsoft.#app_name#.mapstruct;

import com.elitsoft.#app_name#.domain.dto.core.#Base#DTO;
import com.elitsoft.#app_name#.domain.entity.#Base#;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad #Base# y DTO.
 */
@Mapper(componentModel = "spring")
public interface #Base#MapStruct {

    /**
     * Convierte un entidad #Base# a #Base#DTO.
     * @param entity La entidad #Base#.
     * @return El #Base#DTO.
     */
    #Base#DTO toDTO(#Base# entity);

    /**
     * Convierte un #Base#DTO a entidad #Base#.
     * @param dto El #Base#DTO.
     * @return La entidad #Base#.
     */
    #Base# toEntity(#Base#DTO dto);

    /**
     * Convierte una lista de entidades #Base# a una lista de #Base#DTOs.
     * @param entities La lista de entidades #Base#.
     * @return The list of #Base#DTOs.
     */
    List<#Base#DTO> toDTOList(List<#Base#> entities);

    /**
     * Convierte una lista de #Base#DTOs a una lista de entidades #Base# entities.
     * @param dto The list of #Base#DTOs.
     * @return The list of #Base# entities.
     */
    List<#Base#> toEntityList(List<#Base#DTO> dto);
}