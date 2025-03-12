package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EstadoProductoDTO;
import com.elitsoft.servicampo.domain.entity.EstadoProducto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad EstadoProducto y DTO.
 */
@Mapper(componentModel = "spring")
public interface EstadoProductoMapStruct {

    /**
     * Convierte un entidad EstadoProducto a EstadoProductoDTO.
     * @param entity La entidad EstadoProducto.
     * @return El EstadoProductoDTO.
     */
    EstadoProductoDTO toDTO(EstadoProducto entity);

    /**
     * Convierte un EstadoProductoDTO a entidad EstadoProducto.
     * @param dto El EstadoProductoDTO.
     * @return La entidad EstadoProducto.
     */
    EstadoProducto toEntity(EstadoProductoDTO dto);

    /**
     * Convierte una lista de entidades EstadoProducto a una lista de EstadoProductoDTOs.
     * @param entities La lista de entidades EstadoProducto.
     * @return The list of EstadoProductoDTOs.
     */
    List<EstadoProductoDTO> toDTOList(List<EstadoProducto> entities);

    /**
     * Convierte una lista de EstadoProductoDTOs a una lista de entidades EstadoProducto entities.
     * @param dto The list of EstadoProductoDTOs.
     * @return The list of EstadoProducto entities.
     */
    List<EstadoProducto> toEntityList(List<EstadoProductoDTO> dto);
}