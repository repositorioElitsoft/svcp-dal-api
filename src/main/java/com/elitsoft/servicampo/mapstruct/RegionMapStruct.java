package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.RegionDto;
import com.elitsoft.servicampo.domain.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Region y DTO.
 */
@Mapper(componentModel = "spring")
public interface RegionMapStruct {

    /**
     * Convierte un entidad Region a RegionDto.
     * @param entity La entidad Region.
     * @return El RegionDto.
     */
    RegionDto toDto(Region entity);

    /**
     * Convierte un RegionDto a entidad Region.
     * @param dto El RegionDto.
     * @return La entidad Region.
     */
    Region toEntity(RegionDto dto);

    /**
     * Convierte una lista de entidades Region a una lista de RegionDtos.
     * @param entities La lista de entidades Region.
     * @return The list of RegionDtos.
     */
    List<RegionDto> toDtoList(List<Region> entities);

    /**
     * Convierte una lista de RegionDtos a una lista de entidades Region entities.
     * @param dtos The list of RegionDtos.
     * @return The list of Region entities.
     */
    List<Region> toEntityList(List<RegionDto> dtos);
}