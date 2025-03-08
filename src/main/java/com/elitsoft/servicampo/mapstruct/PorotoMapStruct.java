package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.PorotoDTO;
import com.elitsoft.servicampo.domain.entity.Poroto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Poroto y DTO.
 */
@Mapper(componentModel = "spring")
public interface PorotoMapStruct {

    /**
     * Convierte un entidad Poroto a PorotoDTO.
     * @param entity La entidad Poroto.
     * @return El PorotoDTO.
     */
    PorotoDTO toDto(Poroto entity);

    /**
     * Convierte un PorotoDTO a entidad Poroto.
     * @param dto El PorotoDTO.
     * @return La entidad Poroto.
     */
    Poroto toEntity(PorotoDTO dto);

    /**
     * Convierte una lista de entidades Poroto a una lista de PorotoDtos.
     * @param entities La lista de entidades Poroto.
     * @return The list of PorotoDtos.
     */
    List<PorotoDTO> toDtoList(List<Poroto> entities);

    /**
     * Convierte una lista de PorotoDtos a una lista de entidades Poroto entities.
     * @param dtos The list of PorotoDtos.
     * @return The list of Poroto entities.
     */
    List<Poroto> toEntityList(List<PorotoDTO> dtos);
}