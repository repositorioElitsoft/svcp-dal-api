package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoComponenteDTO;
import com.elitsoft.servicampo.domain.entity.TipoComponente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoComponente y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoComponenteMapStruct {

    /**
     * Convierte un entidad TipoComponente a TipoComponenteDTO.
     * @param entity La entidad TipoComponente.
     * @return El TipoComponenteDTO.
     */
    TipoComponenteDTO toDTO(TipoComponente entity);

    /**
     * Convierte un TipoComponenteDTO a entidad TipoComponente.
     * @param dto El TipoComponenteDTO.
     * @return La entidad TipoComponente.
     */
    TipoComponente toEntity(TipoComponenteDTO dto);

    /**
     * Convierte una lista de entidades TipoComponente a una lista de TipoComponenteDTOs.
     * @param entities La lista de entidades TipoComponente.
     * @return The list of TipoComponenteDTOs.
     */
    List<TipoComponenteDTO> toDTOList(List<TipoComponente> entities);

    /**
     * Convierte una lista de TipoComponenteDTOs a una lista de entidades TipoComponente entities.
     * @param dto The list of TipoComponenteDTOs.
     * @return The list of TipoComponente entities.
     */
    List<TipoComponente> toEntityList(List<TipoComponenteDTO> dto);
}