package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ZonaDTO;
import com.elitsoft.servicampo.domain.entity.Zona;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Zona y DTO.
 */
@Mapper(componentModel = "spring")
public interface ZonaMapStruct {

    /**
     * Convierte un entidad Zona a ZonaDTO.
     * @param entity La entidad Zona.
     * @return El ZonaDTO.
     */
    ZonaDTO toDTO(Zona entity);

    /**
     * Convierte un ZonaDTO a entidad Zona.
     * @param dto El ZonaDTO.
     * @return La entidad Zona.
     */
    Zona toEntity(ZonaDTO dto);

    /**
     * Convierte una lista de entidades Zona a una lista de ZonaDtos.
     * @param entities La lista de entidades Zona.
     * @return The list of ZonaDtos.
     */
    List<ZonaDTO> toDTOList(List<Zona> entities);

    /**
     * Convierte una lista de ZonaDtos a una lista de entidades Zona entities.
     * @param dtos The list of ZonaDtos.
     * @return The list of Zona entities.
     */
    List<Zona> toEntityList(List<ZonaDTO> dtos);
}