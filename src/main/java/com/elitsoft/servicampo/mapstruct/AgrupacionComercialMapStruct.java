package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.AgrupacionComercialDTO;
import com.elitsoft.servicampo.domain.entity.AgrupacionComercial;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad AgrupacionComercial y DTO.
 */
@Mapper(componentModel = "spring")
public interface AgrupacionComercialMapStruct {

    /**
     * Convierte un entidad AgrupacionComercial a AgrupacionComercialDto.
     * @param entity La entidad AgrupacionComercial.
     * @return El AgrupacionComercialDto.
     */
    AgrupacionComercialDTO toDto(AgrupacionComercial entity);

    /**
     * Convierte un AgrupacionComercialDto a entidad AgrupacionComercial.
     * @param dto El AgrupacionComercialDto.
     * @return La entidad AgrupacionComercial.
     */
    AgrupacionComercial toEntity(AgrupacionComercialDTO dto);

    /**
     * Convierte una lista de entidades AgrupacionComercial a una lista de AgrupacionComercialDtos.
     * @param entities La lista de entidades AgrupacionComercial.
     * @return The list of AgrupacionComercialDtos.
     */
    List<AgrupacionComercialDTO> toDtoList(List<AgrupacionComercial> entities);

    /**
     * Convierte una lista de AgrupacionComercialDtos a una lista de entidades AgrupacionComercial entities.
     * @param dtos The list of AgrupacionComercialDtos.
     * @return The list of AgrupacionComercial entities.
     */
    List<AgrupacionComercial> toEntityList(List<AgrupacionComercialDTO> dtos);
}