package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ComponenteDTO;
import com.elitsoft.servicampo.domain.entity.Componente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Componente y DTO.
 */
@Mapper(componentModel = "spring")
public interface ComponenteMapStruct {

    /**
     * Convierte un entidad Componente a ComponenteDTO.
     * @param entity La entidad Componente.
     * @return El ComponenteDTO.
     */
    ComponenteDTO toDTO(Componente entity);

    /**
     * Convierte un ComponenteDTO a entidad Componente.
     * @param dto El ComponenteDTO.
     * @return La entidad Componente.
     */
    Componente toEntity(ComponenteDTO dto);

    /**
     * Convierte una lista de entidades Componente a una lista de ComponenteDTOs.
     * @param entities lista de entidades Componente.
     * @return lista de ComponenteDTOs.
     */
    List<ComponenteDTO> toDTOList(List<Componente> entities);

    /**
     * Convierte una lista de ComponenteDTOs a una lista de entidades Componente.
     * @param dto lista de ComponenteDTOs.
     * @return lista de entidades Componente.
     */
    List<Componente> toEntityList(List<ComponenteDTO> dto);
}