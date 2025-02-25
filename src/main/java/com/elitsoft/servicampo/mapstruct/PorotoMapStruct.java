package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.PorotoDto;
import com.elitsoft.servicampo.domain.entity.Poroto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Poroto y DTO.
 */
@Mapper(componentModel = "spring")
public interface PorotoMapStruct {

    /**
     * Convierte un entidad Poroto a PorotoDto.
     * @param entity La entidad Poroto.
     * @return El PorotoDto.
     */
    PorotoDto toDto(Poroto entity);

    /**
     * Convierte un PorotoDto a entidad Poroto.
     * @param dto El PorotoDto.
     * @return La entidad Poroto.
     */
    Poroto toEntity(PorotoDto dto);

    /**
     * Convierte una lista de entidades Poroto a una lista de PorotoDtos.
     * @param entities La lista de entidades Poroto.
     * @return The list of PorotoDtos.
     */
    List<PorotoDto> toDtoList(List<Poroto> entities);

    /**
     * Convierte una lista de PorotoDtos a una lista de entidades Poroto entities.
     * @param dtos The list of PorotoDtos.
     * @return The list of Poroto entities.
     */
    List<Poroto> toEntityList(List<PorotoDto> dtos);
}