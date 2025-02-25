package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.SectorDto;
import com.elitsoft.servicampo.domain.entity.Sector;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Sector y DTO.
 */
@Mapper(componentModel = "spring")
public interface SectorMapStruct {

    /**
     * Convierte un entidad Sector a SectorDto.
     * @param entity La entidad Sector.
     * @return El SectorDto.
     */
    SectorDto toDto(Sector entity);

    /**
     * Convierte un SectorDto a entidad Sector.
     * @param dto El SectorDto.
     * @return La entidad Sector.
     */
    Sector toEntity(SectorDto dto);

    /**
     * Convierte una lista de entidades Sector a una lista de SectorDtos.
     * @param entities La lista de entidades Sector.
     * @return The list of SectorDtos.
     */
    List<SectorDto> toDtoList(List<Sector> entities);

    /**
     * Convierte una lista de SectorDtos a una lista de entidades Sector entities.
     * @param dtos The list of SectorDtos.
     * @return The list of Sector entities.
     */
    List<Sector> toEntityList(List<SectorDto> dtos);
}