package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.SectorDTO;
import com.elitsoft.servicampo.domain.entity.Sector;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Sector y DTO.
 */
@Mapper(componentModel = "spring")
public interface SectorMapStruct {

    /**
     * Convierte un entidad Sector a SectorDTO.
     * @param entity La entidad Sector.
     * @return El SectorDTO.
     */
    SectorDTO toDto(Sector entity);

    /**
     * Convierte un SectorDTO a entidad Sector.
     * @param dto El SectorDTO.
     * @return La entidad Sector.
     */
    Sector toEntity(SectorDTO dto);

    /**
     * Convierte una lista de entidades Sector a una lista de SectorDtos.
     * @param entities La lista de entidades Sector.
     * @return The list of SectorDtos.
     */
    List<SectorDTO> toDtoList(List<Sector> entities);

    /**
     * Convierte una lista de SectorDtos a una lista de entidades Sector entities.
     * @param dtos The list of SectorDtos.
     * @return The list of Sector entities.
     */
    List<Sector> toEntityList(List<SectorDTO> dtos);
}