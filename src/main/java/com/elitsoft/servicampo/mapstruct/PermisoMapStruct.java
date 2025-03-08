package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.PermisoDTO;
import com.elitsoft.servicampo.domain.entity.Permiso;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Permiso y DTO.
 */
@Mapper(componentModel = "spring")
public interface PermisoMapStruct {

    /**
     * Convierte un entidad Permiso a PermisoDTO.
     * @param entity La entidad Permiso.
     * @return El PermisoDTO.
     */
    PermisoDTO toDto(Permiso entity);

    /**
     * Convierte un PermisoDTO a entidad Permiso.
     * @param dto El PermisoDTO.
     * @return La entidad Permiso.
     */
    Permiso toEntity(PermisoDTO dto);

    /**
     * Convierte una lista de entidades Permiso a una lista de PermisoDtos.
     * @param entities La lista de entidades Permiso.
     * @return The list of PermisoDtos.
     */
    List<PermisoDTO> toDtoList(List<Permiso> entities);

    /**
     * Convierte una lista de PermisoDtos a una lista de entidades Permiso entities.
     * @param dtos The list of PermisoDtos.
     * @return The list of Permiso entities.
     */
    List<Permiso> toEntityList(List<PermisoDTO> dtos);
}