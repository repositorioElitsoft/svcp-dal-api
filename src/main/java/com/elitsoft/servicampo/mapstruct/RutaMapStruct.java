package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.RutaDTO;
import com.elitsoft.servicampo.domain.entity.Ruta;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Ruta y DTO.
 */
@Mapper(componentModel = "spring")
public interface RutaMapStruct {

    /**
     * Convierte un entidad Ruta a RutaDTO.
     * @param entity La entidad Ruta.
     * @return El RutaDTO.
     */
    RutaDTO toDTO(Ruta entity);

    /**
     * Convierte un RutaDTO a entidad Ruta.
     * @param dto El RutaDTO.
     * @return La entidad Ruta.
     */
    Ruta toEntity(RutaDTO dto);

    /**
     * Convierte una lista de entidades Ruta a una lista de RutaDTOs.
     * @param entities La lista de entidades Ruta.
     * @return The list of RutaDTOs.
     */
    List<RutaDTO> toDTOList(List<Ruta> entities);

    /**
     * Convierte una lista de RutaDTOs a una lista de entidades Ruta entities.
     * @param dto The list of RutaDTOs.
     * @return The list of Ruta entities.
     */
    List<Ruta> toEntityList(List<RutaDTO> dto);
}