package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ProvinciaDto;
import com.elitsoft.servicampo.domain.entity.Provincia;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Provincia y DTO.
 */
@Mapper(componentModel = "spring")
public interface ProvinciaMapStruct {

    /**
     * Convierte un entidad Provincia a ProvinciaDto.
     * @param entity La entidad Provincia.
     * @return El ProvinciaDto.
     */
    ProvinciaDto toDto(Provincia entity);

    /**
     * Convierte un ProvinciaDto a entidad Provincia.
     * @param dto El ProvinciaDto.
     * @return La entidad Provincia.
     */
    Provincia toEntity(ProvinciaDto dto);

    /**
     * Convierte una lista de entidades Provincia a una lista de ProvinciaDtos.
     * @param entities La lista de entidades Provincia.
     * @return The list of ProvinciaDtos.
     */
    List<ProvinciaDto> toDtoList(List<Provincia> entities);

    /**
     * Convierte una lista de ProvinciaDtos a una lista de entidades Provincia entities.
     * @param dtos The list of ProvinciaDtos.
     * @return The list of Provincia entities.
     */
    List<Provincia> toEntityList(List<ProvinciaDto> dtos);
}