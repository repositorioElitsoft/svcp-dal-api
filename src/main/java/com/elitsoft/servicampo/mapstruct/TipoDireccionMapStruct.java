package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoDireccionDTO;
import com.elitsoft.servicampo.domain.entity.TipoDireccion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoDireccion y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoDireccionMapStruct {

    /**
     * Convierte un entidad TipoDireccion a TipoDireccionDTO.
     * @param entity La entidad TipoDireccion.
     * @return El TipoDireccionDTO.
     */
    TipoDireccionDTO toDTO(TipoDireccion entity);

    /**
     * Convierte un TipoDireccionDTO a entidad TipoDireccion.
     * @param dto El TipoDireccionDTO.
     * @return La entidad TipoDireccion.
     */
    TipoDireccion toEntity(TipoDireccionDTO dto);

    /**
     * Convierte una lista de entidades TipoDireccion a una lista de TipoDireccionDTOs.
     * @param entities La lista de entidades TipoDireccion.
     * @return The list of TipoDireccionDTOs.
     */
    List<TipoDireccionDTO> toDTOList(List<TipoDireccion> entities);

    /**
     * Convierte una lista de TipoDireccionDTOs a una lista de entidades TipoDireccion entities.
     * @param dto The list of TipoDireccionDTOs.
     * @return The list of TipoDireccion entities.
     */
    List<TipoDireccion> toEntityList(List<TipoDireccionDTO> dto);
}