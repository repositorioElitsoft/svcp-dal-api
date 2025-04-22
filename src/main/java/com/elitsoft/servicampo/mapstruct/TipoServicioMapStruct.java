package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoServicioDTO;
import com.elitsoft.servicampo.domain.entity.TipoServicio;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoServicio y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoServicioMapStruct {

    /**
     * Convierte un entidad TipoServicio a TipoServicioDTO.
     * @param entity La entidad TipoServicio.
     * @return El TipoServicioDTO.
     */
    TipoServicioDTO toDTO(TipoServicio entity);

    /**
     * Convierte un TipoServicioDTO a entidad TipoServicio.
     * @param dto El TipoServicioDTO.
     * @return La entidad TipoServicio.
     */
    TipoServicio toEntity(TipoServicioDTO dto);

    /**
     * Convierte una lista de entidades TipoServicio a una lista de TipoServicioDtos.
     * @param entities La lista de entidades TipoServicio.
     * @return The list of TipoServicioDtos.
     */
    List<TipoServicioDTO> toDTOList(List<TipoServicio> entities);

    /**
     * Convierte una lista de TipoServicioDtos a una lista de entidades TipoServicio entities.
     * @param dtos The list of TipoServicioDtos.
     * @return The list of TipoServicio entities.
     */
    List<TipoServicio> toEntityList(List<TipoServicioDTO> dtos);
}