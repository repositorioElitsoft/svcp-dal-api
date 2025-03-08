package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ProvinciaDTO;
import com.elitsoft.servicampo.domain.entity.Provincia;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Provincia y DTO.
 */
@Mapper(componentModel = "spring")
public interface ProvinciaMapStruct {

    /**
     * Convierte un entidad Provincia a ProvinciaDTO.
     * @param entity La entidad Provincia.
     * @return El ProvinciaDTO.
     */
    ProvinciaDTO toDto(Provincia entity);

    /**
     * Convierte un ProvinciaDTO a entidad Provincia.
     * @param dto El ProvinciaDTO.
     * @return La entidad Provincia.
     */
    Provincia toEntity(ProvinciaDTO dto);

    /**
     * Convierte una lista de entidades Provincia a una lista de ProvinciaDtos.
     * @param entities La lista de entidades Provincia.
     * @return The list of ProvinciaDtos.
     */
    List<ProvinciaDTO> toDtoList(List<Provincia> entities);

    /**
     * Convierte una lista de ProvinciaDtos a una lista de entidades Provincia entities.
     * @param dtos The list of ProvinciaDtos.
     * @return The list of Provincia entities.
     */
    List<Provincia> toEntityList(List<ProvinciaDTO> dtos);
}