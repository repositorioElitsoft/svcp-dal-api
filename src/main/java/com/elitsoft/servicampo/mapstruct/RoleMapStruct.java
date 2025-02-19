package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.RoleDto;
import com.elitsoft.servicampo.domain.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Role y DTO.
 */
@Mapper(componentModel = "spring")
public interface RoleMapStruct {

    /**
     * Convierte un entidad Role a RoleDto.
     * @param entity La entidad Role.
     * @return El RoleDto.
     */
    RoleDto toDto(Role entity);

    /**
     * Convierte un RoleDto a entidad Role.
     * @param dto El RoleDto.
     * @return La entidad Role.
     */
    Role toEntity(RoleDto dto);

    /**
     * Convierte una lista de entidades Role a una lista de RoleDtos.
     * @param entities La lista de entidades Role.
     * @return The list of RoleDtos.
     */
    List<RoleDto> toDtoList(List<Role> entities);

    /**
     * Convierte una lista de RoleDtos a una lista de entidades Role entities.
     * @param dtos The list of RoleDtos.
     * @return The list of Role entities.
     */
    List<Role> toEntityList(List<RoleDto> dtos);
}