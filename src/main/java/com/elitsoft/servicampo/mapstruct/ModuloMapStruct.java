package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ModuloDto;
import com.elitsoft.servicampo.domain.entity.Modulo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Modulo y DTO.
 */
@Mapper(componentModel = "spring")
public interface ModuloMapStruct {

    /**
     * Convierte un entidad Modulo a ModuloDto.
     * @param entity La entidad Modulo.
     * @return El ModuloDto.
     */
    ModuloDto toDto(Modulo entity);

    /**
     * Convierte un ModuloDto a entidad Modulo.
     * @param dto El ModuloDto.
     * @return La entidad Modulo.
     */
    Modulo toEntity(ModuloDto dto);

    /**
     * Convierte una lista de entidades Modulo a una lista de ModuloDtos.
     * @param entities La lista de entidades Modulo.
     * @return The list of ModuloDtos.
     */
    List<ModuloDto> toDtoList(List<Modulo> entities);

    /**
     * Convierte una lista de ModuloDtos a una lista de entidades Modulo entities.
     * @param dtos The list of ModuloDtos.
     * @return The list of Modulo entities.
     */
    List<Modulo> toEntityList(List<ModuloDto> dtos);
}