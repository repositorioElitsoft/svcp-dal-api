package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoProducto y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoProductoMapStruct {

    /**
     * Convierte un entidad TipoProducto a TipoProductoDto.
     * @param entity La entidad TipoProducto.
     * @return El TipoProductoDto.
     */
    TipoProductoDTO toDto(TipoProducto entity);

    /**
     * Convierte un TipoProductoDto a entidad TipoProducto.
     * @param dto El TipoProductoDto.
     * @return La entidad TipoProducto.
     */
    TipoProducto toEntity(TipoProductoDTO dto);

    /**
     * Convierte una lista de entidades TipoProducto a una lista de TipoProductoDtos.
     * @param entities La lista de entidades TipoProducto.
     * @return The list of TipoProductoDtos.
     */
    List<TipoProductoDTO> toDtoList(List<TipoProducto> entities);

    /**
     * Convierte una lista de TipoProductoDtos a una lista de entidades TipoProducto entities.
     * @param dtos The list of TipoProductoDtos.
     * @return The list of TipoProducto entities.
     */
    List<TipoProducto> toEntityList(List<TipoProductoDTO> dtos);
}