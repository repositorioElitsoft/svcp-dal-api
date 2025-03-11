package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.DireccionDTO;
import com.elitsoft.servicampo.domain.entity.Direccion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Direccion y DTO.
 */
@Mapper(componentModel = "spring")
public interface DireccionMapStruct {

    /**
     * Convierte un entidad Direccion a DireccionDTO.
     * @param entity La entidad Direccion.
     * @return El DireccionDTO.
     */
    DireccionDTO toDTO(Direccion entity);

    /**
     * Convierte un DireccionDTO a entidad Direccion.
     * @param dto El DireccionDTO.
     * @return La entidad Direccion.
     */
    Direccion toEntity(DireccionDTO dto);

    /**
     * Convierte una lista de entidades Direccion a una lista de DireccionDTOs.
     * @param entities La lista de entidades Direccion.
     * @return The list of DireccionDTOs.
     */
    List<DireccionDTO> toDTOList(List<Direccion> entities);

    /**
     * Convierte una lista de DireccionDTOs a una lista de entidades Direccion entities.
     * @param dto The list of DireccionDTOs.
     * @return The list of Direccion entities.
     */
    List<Direccion> toEntityList(List<DireccionDTO> dto);
}