package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.SubMenuDto;
import com.elitsoft.servicampo.domain.entity.SubMenu;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad SubMenu y DTO.
 */
@Mapper(componentModel = "spring")
public interface SubMenuMapStruct {

    /**
     * Convierte un entidad SubMenu a SubMenuDto.
     * @param entity La entidad SubMenu.
     * @return El SubMenuDto.
     */
    SubMenuDto toDto(SubMenu entity);

    /**
     * Convierte un SubMenuDto a entidad SubMenu.
     * @param dto El SubMenuDto.
     * @return La entidad SubMenu.
     */
    SubMenu toEntity(SubMenuDto dto);

    /**
     * Convierte una lista de entidades SubMenu a una lista de SubMenuDtos.
     * @param entities La lista de entidades SubMenu.
     * @return The list of SubMenuDtos.
     */
    List<SubMenuDto> toDtoList(List<SubMenu> entities);

    /**
     * Convierte una lista de SubMenuDtos a una lista de entidades SubMenu entities.
     * @param dtos The list of SubMenuDtos.
     * @return The list of SubMenu entities.
     */
    List<SubMenu> toEntityList(List<SubMenuDto> dtos);
}