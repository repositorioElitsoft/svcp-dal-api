package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.RegionDTO;
import com.elitsoft.servicampo.domain.entity.Region;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Region y DTO.
 */
@Mapper(componentModel = "spring")
public interface RegionMapStruct {

    /**
     * Convierte un entidad Region a RegionDTO.
     * @param entity La entidad Region.
     * @return El RegionDTO.
     */
    RegionDTO toDto(Region entity);

    /**
     * Convierte un RegionDTO a entidad Region.
     * @param dto El RegionDTO.
     * @return La entidad Region.
     */
    Region toEntity(RegionDTO dto);

    /**
     * Convierte una lista de entidades Region a una lista de RegionDtos.
     * @param entities La lista de entidades Region.
     * @return The list of RegionDtos.
     */
    List<RegionDTO> toDtoList(List<Region> entities);

    /**
     * Convierte una lista de RegionDtos a una lista de entidades Region entities.
     * @param dtos The list of RegionDtos.
     * @return The list of Region entities.
     */
    List<Region> toEntityList(List<RegionDTO> dtos);
}