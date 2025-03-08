package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.PaisDTO;
import com.elitsoft.servicampo.domain.entity.Pais;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Pais y DTO.
 */
@Mapper(componentModel = "spring")
public interface PaisMapStruct {

    /**
     * Convierte un entidad Pais a PaisDTO.
     * @param entity La entidad Pais.
     * @return El PaisDTO.
     */
    //@Mapping(target = "regiones", ignore = true) // Add this annotation
    PaisDTO toDto(Pais entity);

    /**
     * Convierte un PaisDTO a entidad Pais.
     * @param dto El PaisDTO.
     * @return La entidad Pais.
     */
    Pais toEntity(PaisDTO dto);

    /**
     * Convierte una lista de entidades Pais a una lista de PaisDtos.
     * @param entities La lista de entidades Pais.
     * @return The list of PaisDtos.
     */
    List<PaisDTO> toDtoList(List<Pais> entities);

    /**
     * Convierte una lista de PaisDtos a una lista de entidades Pais entities.
     * @param dtos The list of PaisDtos.
     * @return The list of Pais entities.
     */
    List<Pais> toEntityList(List<PaisDTO> dtos);
}