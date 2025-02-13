package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.CarritoArticuloDto;
import com.elitsoft.servicampo.domain.entity.CarritoArticulo;
import org.mapstruct.Mapper;



import java.util.List;

/**
 *
 */
@Mapper(componentModel = "spring")
public interface CarritoArticuloMapStruct { //Renamed interface

    /**
     * @param entity
     * @return
     */
    //@Mapping(target = "id", ignore = true) // Add this annotation
    CarritoArticuloDto toDto(CarritoArticulo entity);

    /**
     * @param dto
     * @return
     */
    CarritoArticulo toEntity(CarritoArticuloDto dto);

    /**
     * @param entities
     * @return
     */
    List<CarritoArticuloDto> toDtoList(List<CarritoArticulo> entities);

    /**
     * @param dtos
     * @return
     */
    List<CarritoArticulo> toEntityList(List<CarritoArticuloDto> dtos);
}