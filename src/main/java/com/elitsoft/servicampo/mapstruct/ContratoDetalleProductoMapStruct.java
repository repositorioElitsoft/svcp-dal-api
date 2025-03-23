package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleProductoDTO;
import com.elitsoft.servicampo.domain.entity.ContratoDetalleProducto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad ContratoDetalleProducto y DTO.
 */
@Mapper(componentModel = "spring")
public interface ContratoDetalleProductoMapStruct {

    /**
     * Convierte un entidad ContratoDetalleProducto a ContratoDetalleProductoDTO.
     * @param entity La entidad ContratoDetalleProducto.
     * @return El ContratoDetalleProductoDTO.
     */
    ContratoDetalleProductoDTO toDTO(ContratoDetalleProducto entity);

    /**
     * Convierte un ContratoDetalleProductoDTO a entidad ContratoDetalleProducto.
     * @param dto El ContratoDetalleProductoDTO.
     * @return La entidad ContratoDetalleProducto.
     */
    ContratoDetalleProducto toEntity(ContratoDetalleProductoDTO dto);

    /**
     * Convierte una lista de entidades ContratoDetalleProducto a una lista de ContratoDetalleProductoDTOs.
     * @param entities La lista de entidades ContratoDetalleProducto.
     * @return The list of ContratoDetalleProductoDTOs.
     */
    List<ContratoDetalleProductoDTO> toDTOList(List<ContratoDetalleProducto> entities);

    /**
     * Convierte una lista de ContratoDetalleProductoDTOs a una lista de entidades ContratoDetalleProducto entities.
     * @param dto The list of ContratoDetalleProductoDTOs.
     * @return The list of ContratoDetalleProducto entities.
     */
    List<ContratoDetalleProducto> toEntityList(List<ContratoDetalleProductoDTO> dto);
}