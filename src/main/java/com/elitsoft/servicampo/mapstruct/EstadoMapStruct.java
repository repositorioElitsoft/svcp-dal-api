package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EstadoDTO;
import com.elitsoft.servicampo.domain.entity.Estado;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Estado y DTO.
 */
@Mapper(componentModel = "spring")
public interface EstadoMapStruct {

    /**
     * Convierte un entidad Estado a EstadoDTO.
     * @param entity La entidad Estado.
     * @return El EstadoDTO.
     */
    EstadoDTO toDTO(Estado entity);

    /**
     * Convierte un EstadoDTO a entidad Estado.
     * @param dto El EstadoDTO.
     * @return La entidad Estado.
     */
    Estado toEntity(EstadoDTO dto);

    /**
     * Convierte una lista de entidades Estado a una lista de EstadoDTOs.
     * @param entities La lista de entidades Estado.
     * @return The list of EstadoDTOs.
     */
    List<EstadoDTO> toDTOList(List<Estado> entities);

    /**
     * Convierte una lista de EstadoDTOs a una lista de entidades Estado entities.
     * @param dto The list of EstadoDTOs.
     * @return The list of Estado entities.
     */
    List<Estado> toEntityList(List<EstadoDTO> dto);
}