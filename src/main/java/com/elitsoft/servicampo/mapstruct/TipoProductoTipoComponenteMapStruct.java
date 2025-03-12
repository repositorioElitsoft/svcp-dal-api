package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoTipoComponenteDTO;
import com.elitsoft.servicampo.domain.entity.TipoProductoTipoComponente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoProductoTipoComponente y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoProductoTipoComponenteMapStruct {

    /**
     * Convierte un entidad TipoProductoTipoComponente a TipoProductoTipoComponenteDTO.
     * @param entity La entidad TipoProductoTipoComponente.
     * @return El TipoProductoTipoComponenteDTO.
     */
    TipoProductoTipoComponenteDTO toDTO(TipoProductoTipoComponente entity);

    /**
     * Convierte un TipoProductoTipoComponenteDTO a entidad TipoProductoTipoComponente.
     * @param dto El TipoProductoTipoComponenteDTO.
     * @return La entidad TipoProductoTipoComponente.
     */
    TipoProductoTipoComponente toEntity(TipoProductoTipoComponenteDTO dto);

    /**
     * Convierte una lista de entidades TipoProductoTipoComponente a una lista de TipoProductoTipoComponenteDTOs.
     * @param entities La lista de entidades TipoProductoTipoComponente.
     * @return The list of TipoProductoTipoComponenteDTOs.
     */
    List<TipoProductoTipoComponenteDTO> toDTOList(List<TipoProductoTipoComponente> entities);

    /**
     * Convierte una lista de TipoProductoTipoComponenteDTOs a una lista de entidades TipoProductoTipoComponente entities.
     * @param dto The list of TipoProductoTipoComponenteDTOs.
     * @return The list of TipoProductoTipoComponente entities.
     */
    List<TipoProductoTipoComponente> toEntityList(List<TipoProductoTipoComponenteDTO> dto);
}