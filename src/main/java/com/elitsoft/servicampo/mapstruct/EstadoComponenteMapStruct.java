package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.EstadoComponenteDTO;
import com.elitsoft.servicampo.domain.entity.EstadoComponente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad EstadoComponente y DTO.
 */
@Mapper(componentModel = "spring")
public interface EstadoComponenteMapStruct {

    /**
     * Convierte un entidad EstadoComponente a EstadoComponenteDTO.
     * @param entity La entidad EstadoComponente.
     * @return El EstadoComponenteDTO.
     */
    EstadoComponenteDTO toDTO(EstadoComponente entity);

    /**
     * Convierte un EstadoComponenteDTO a entidad EstadoComponente.
     * @param dto El EstadoComponenteDTO.
     * @return La entidad EstadoComponente.
     */
    EstadoComponente toEntity(EstadoComponenteDTO dto);

    /**
     * Convierte una lista de entidades EstadoComponente a una lista de EstadoComponenteDTOs.
     * @param entities lista de entidades EstadoComponente.
     * @return lista de EstadoComponenteDTOs.
     */
    List<EstadoComponenteDTO> toDTOList(List<EstadoComponente> entities);

    /**
     * Convierte una lista de EstadoComponenteDTOs a una lista de entidades EstadoComponente.
     * @param dto lista de EstadoComponenteDTOs.
     * @return lista de entidades EstadoComponente.
     */
    List<EstadoComponente> toEntityList(List<EstadoComponenteDTO> dto);
}