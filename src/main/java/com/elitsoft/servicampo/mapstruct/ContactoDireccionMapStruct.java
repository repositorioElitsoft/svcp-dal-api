package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ContactoDireccionDTO;
import com.elitsoft.servicampo.domain.entity.ContactoDireccion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Contacto y DTO.
 */
@Mapper(componentModel = "spring")
public interface ContactoDireccionMapStruct {

    /**
     * Convierte un entidad ContactoDireccion a ContactoDireccionDTO.
     * @param entity La entidad ContactoDireccion.
     * @return El ContactoDireccionDTO.
     */
    ContactoDireccionDTO toDTO(ContactoDireccion entity);

    /**
     * Convierte un ContactoDireccionDTO a entidad ContactoDireccion.
     * @param dto El ContactoDireccionDTO.
     * @return La entidad ContactoDireccion.
     */
    ContactoDireccion toEntity(ContactoDireccionDTO dto);

    /**
     * Convierte una lista de entidades ContactoDireccion a una lista de ContactoDireccionDTO.
     * @param entities La lista de entidades ContactoDireccion.
     * @return The list of ContactoDireccionDTO.
     */
    List<ContactoDireccionDTO> toDTOList(List<ContactoDireccion> entities);

    /**
     * Convierte una lista de ContactoDTOs a una lista de entidades Contacto entities.
     * @param dto The list of ContactoDTOs.
     * @return The list of ContactoDireccion entities.
     */
    List<ContactoDireccion> toEntityList(List<ContactoDireccionDTO> dto);
}