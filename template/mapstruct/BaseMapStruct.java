package com.elitsoft.#app_name#.mapstruct;

import com.elitsoft.#app_name#.domain.dto.core.#Base#Dto;
import com.elitsoft.#app_name#.domain.entity.#Base#;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad #Base# y DTO.
 */
@Mapper(componentModel = "spring")
public interface #Base#MapStruct {

    /**
     * Convierte un entidad #Base# a #Base#Dto.
     * @param entity La entidad #Base#.
     * @return El #Base#Dto.
     */
    #Base#Dto toDto(#Base# entity);

    /**
     * Convierte un #Base#Dto a entidad #Base#.
     * @param dto El #Base#Dto.
     * @return La entidad #Base#.
     */
    #Base# toEntity(#Base#Dto dto);

    /**
     * Convierte una lista de entidades #Base# a una lista de #Base#Dtos.
     * @param entities La lista de entidades #Base#.
     * @return The list of #Base#Dtos.
     */
    List<#Base#Dto> toDtoList(List<#Base#> entities);

    /**
     * Convierte una lista de #Base#Dtos a una lista de entidades #Base# entities.
     * @param dtos The list of #Base#Dtos.
     * @return The list of #Base# entities.
     */
    List<#Base#> toEntityList(List<#Base#Dto> dtos);
}