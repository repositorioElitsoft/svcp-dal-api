package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.PaisDto;
import com.elitsoft.servicampo.domain.entity.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Pais y DTO.
 */
@Mapper(componentModel = "spring")
public interface PaisMapStruct {

    /**
     * Convierte un entidad Pais a PaisDto.
     * @param entity La entidad Pais.
     * @return El PaisDto.
     */
    //@Mapping(target = "regiones", ignore = true) // Add this annotation
    PaisDto toDto(Pais entity);

    /**
     * Convierte un PaisDto a entidad Pais.
     * @param dto El PaisDto.
     * @return La entidad Pais.
     */
    Pais toEntity(PaisDto dto);

    /**
     * Convierte una lista de entidades Pais a una lista de PaisDtos.
     * @param entities La lista de entidades Pais.
     * @return The list of PaisDtos.
     */
    @Mapping(target = "regiones", ignore = true) // Add this annotation
    List<PaisDto> toDtoList(List<Pais> entities);

    /**
     * Convierte una lista de PaisDtos a una lista de entidades Pais entities.
     * @param dtos The list of PaisDtos.
     * @return The list of Pais entities.
     */
    List<Pais> toEntityList(List<PaisDto> dtos);
}