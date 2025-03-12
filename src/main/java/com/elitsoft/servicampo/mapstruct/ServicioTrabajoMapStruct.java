package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ServicioTrabajoDTO;
import com.elitsoft.servicampo.domain.entity.ServicioTrabajo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad ServicioTrabajo y DTO.
 */
@Mapper(componentModel = "spring")
public interface ServicioTrabajoMapStruct {

    /**
     * Convierte un entidad ServicioTrabajo a ServicioTrabajoDTO.
     * @param entity La entidad ServicioTrabajo.
     * @return El ServicioTrabajoDTO.
     */
    ServicioTrabajoDTO toDTO(ServicioTrabajo entity);

    /**
     * Convierte un ServicioTrabajoDTO a entidad ServicioTrabajo.
     * @param dto El ServicioTrabajoDTO.
     * @return La entidad ServicioTrabajo.
     */
    ServicioTrabajo toEntity(ServicioTrabajoDTO dto);

    /**
     * Convierte una lista de entidades ServicioTrabajo a una lista de ServicioTrabajoDTOs.
     * @param entities La lista de entidades ServicioTrabajo.
     * @return The list of ServicioTrabajoDTOs.
     */
    List<ServicioTrabajoDTO> toDTOList(List<ServicioTrabajo> entities);

    /**
     * Convierte una lista de ServicioTrabajoDTOs a una lista de entidades ServicioTrabajo entities.
     * @param dto The list of ServicioTrabajoDTOs.
     * @return The list of ServicioTrabajo entities.
     */
    List<ServicioTrabajo> toEntityList(List<ServicioTrabajoDTO> dto);
}