package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoServicioDto;
import com.elitsoft.servicampo.domain.entity.TipoServicio;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoServicio y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoServicioMapStruct {

    /**
     * Convierte un entidad TipoServicio a TipoServicioDto.
     * @param entity La entidad TipoServicio.
     * @return El TipoServicioDto.
     */
    TipoServicioDto toDto(TipoServicio entity);

    /**
     * Convierte un TipoServicioDto a entidad TipoServicio.
     * @param dto El TipoServicioDto.
     * @return La entidad TipoServicio.
     */
    TipoServicio toEntity(TipoServicioDto dto);

    /**
     * Convierte una lista de entidades TipoServicio a una lista de TipoServicioDtos.
     * @param entities La lista de entidades TipoServicio.
     * @return The list of TipoServicioDtos.
     */
    List<TipoServicioDto> toDtoList(List<TipoServicio> entities);

    /**
     * Convierte una lista de TipoServicioDtos a una lista de entidades TipoServicio entities.
     * @param dtos The list of TipoServicioDtos.
     * @return The list of TipoServicio entities.
     */
    List<TipoServicio> toEntityList(List<TipoServicioDto> dtos);
}