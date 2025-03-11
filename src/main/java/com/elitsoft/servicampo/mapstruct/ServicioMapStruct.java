package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ServicioDTO;
import com.elitsoft.servicampo.domain.entity.Servicio;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Servicio y DTO.
 */
@Mapper(componentModel = "spring")
public interface ServicioMapStruct {

    /**
     * Convierte un entidad Servicio a ServicioDTO.
     * @param entity La entidad Servicio.
     * @return El ServicioDTO.
     */
    ServicioDTO toDTO(Servicio entity);

    /**
     * Convierte un ServicioDTO a entidad Servicio.
     * @param dto El ServicioDTO.
     * @return La entidad Servicio.
     */
    Servicio toEntity(ServicioDTO dto);

    /**
     * Convierte una lista de entidades Servicio a una lista de ServicioDTOs.
     * @param entities La lista de entidades Servicio.
     * @return The list of ServicioDTOs.
     */
    List<ServicioDTO> toDTOList(List<Servicio> entities);

    /**
     * Convierte una lista de ServicioDTOs a una lista de entidades Servicio entities.
     * @param dto The list of ServicioDTOs.
     * @return The list of Servicio entities.
     */
    List<Servicio> toEntityList(List<ServicioDTO> dto);
}