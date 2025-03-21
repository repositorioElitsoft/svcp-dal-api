package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleTipoProductoDTO;
import com.elitsoft.servicampo.domain.entity.ContratoDetalleTipoProducto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad ContratoDetalleTipoProducto y DTO.
 */
@Mapper(componentModel = "spring")
public interface ContratoDetalleTipoProductoMapStruct {

    /**
     * Convierte un entidad ContratoDetalleTipoProducto a ContratoDetalleTipoProductoDTO.
     * @param entity La entidad ContratoDetalleTipoProducto.
     * @return El ContratoDetalleTipoProductoDTO.
     */
    ContratoDetalleTipoProductoDTO toDTO(ContratoDetalleTipoProducto entity);

    /**
     * Convierte un ContratoDetalleTipoProductoDTO a entidad ContratoDetalleTipoProducto.
     * @param dto El ContratoDetalleTipoProductoDTO.
     * @return La entidad ContratoDetalleTipoProducto.
     */
    ContratoDetalleTipoProducto toEntity(ContratoDetalleTipoProductoDTO dto);

    /**
     * Convierte una lista de entidades ContratoDetalleTipoProducto a una lista de ContratoDetalleTipoProductoDTOs.
     * @param entities La lista de entidades ContratoDetalleTipoProducto.
     * @return The list of ContratoDetalleTipoProductoDTOs.
     */
    List<ContratoDetalleTipoProductoDTO> toDTOList(List<ContratoDetalleTipoProducto> entities);

    /**
     * Convierte una lista de ContratoDetalleTipoProductoDTOs a una lista de entidades ContratoDetalleTipoProducto entities.
     * @param dto The list of ContratoDetalleTipoProductoDTOs.
     * @return The list of ContratoDetalleTipoProducto entities.
     */
    List<ContratoDetalleTipoProducto> toEntityList(List<ContratoDetalleTipoProductoDTO> dto);
}