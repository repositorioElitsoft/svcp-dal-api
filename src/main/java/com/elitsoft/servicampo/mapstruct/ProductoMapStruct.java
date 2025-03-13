package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ProductoDTO;
import com.elitsoft.servicampo.domain.entity.Producto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Producto y DTO.
 */
@Mapper(componentModel = "spring")
public interface ProductoMapStruct {

    /**
     * Convierte un entidad Producto a ProductoDTO.
     * @param entity La entidad Producto.
     * @return El ProductoDTO.
     */
    ProductoDTO toDTO(Producto entity);

    /**
     * Convierte un ProductoDTO a entidad Producto.
     * @param dto El ProductoDTO.
     * @return La entidad Producto.
     */
    Producto toEntity(ProductoDTO dto);

    /**
     * Convierte una lista de entidades Producto a una lista de ProductoDTOs.
     * @param entities La lista de entidades Producto.
     * @return The list of ProductoDTOs.
     */
    List<ProductoDTO> toDTOList(List<Producto> entities);

    /**
     * Convierte una lista de ProductoDTOs a una lista de entidades Producto entities.
     * @param dto The list of ProductoDTOs.
     * @return The list of Producto entities.
     */
    List<Producto> toEntityList(List<ProductoDTO> dto);
}