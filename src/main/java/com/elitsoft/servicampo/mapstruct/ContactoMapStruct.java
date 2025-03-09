package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.domain.entity.Contacto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Contacto y DTO.
 */
@Mapper(componentModel = "spring")
public interface ContactoMapStruct {

    /**
     * Convierte un entidad Contacto a ContactoDTO.
     * @param entity La entidad Contacto.
     * @return El ContactoDTO.
     */
    ContactoDTO toDTO(Contacto entity);

    /**
     * Convierte un ContactoDTO a entidad Contacto.
     * @param dto El ContactoDTO.
     * @return La entidad Contacto.
     */
    Contacto toEntity(ContactoDTO dto);

    /**
     * Convierte una lista de entidades Contacto a una lista de ContactoDTOs.
     * @param entities La lista de entidades Contacto.
     * @return The list of ContactoDTOs.
     */
    List<ContactoDTO> toDTOList(List<Contacto> entities);

    /**
     * Convierte una lista de ContactoDTOs a una lista de entidades Contacto entities.
     * @param dto The list of ContactoDTOs.
     * @return The list of Contacto entities.
     */
    List<Contacto> toEntityList(List<ContactoDTO> dto);
}