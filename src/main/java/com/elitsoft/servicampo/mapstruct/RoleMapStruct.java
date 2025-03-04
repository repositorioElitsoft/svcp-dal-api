package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.RoleDTO;
import com.elitsoft.servicampo.domain.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Role y DTO.
 */
@Mapper(componentModel = "spring")
public interface RoleMapStruct {

    /**
     * Convierte un entidad Role a RoleDTO.
     * @param entity La entidad Role.
     * @return El RoleDTO.
     */
    RoleDTO toDTO(Role entity);

    /**
     * Convierte un RoleDTO a entidad Role.
     * @param dto El RoleDTO.
     * @return La entidad Role.
     */
    Role toEntity(RoleDTO dto);

    /**
     * Convierte una lista de entidades Role a una lista de RoleDTOs.
     * @param entities La lista de entidades Role.
     * @return The list of RoleDTOs.
     */
    List<RoleDTO> toDTOList(List<Role> entities);

    /**
     * Convierte una lista de RoleDTOs a una lista de entidades Role entities.
     * @param dto The list of RoleDTOs.
     * @return The list of Role entities.
     */
    List<Role> toEntityList(List<RoleDTO> dto);
}