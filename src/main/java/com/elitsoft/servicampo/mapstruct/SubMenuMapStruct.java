package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.SubMenuDTO;
import com.elitsoft.servicampo.domain.entity.SubMenu;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad SubMenu y DTO.
 */
@Mapper(componentModel = "spring")
public interface SubMenuMapStruct {

    /**
     * Convierte un entidad SubMenu a SubMenuDTO.
     * @param entity La entidad SubMenu.
     * @return El SubMenuDTO.
     */
    SubMenuDTO toDto(SubMenu entity);

    /**
     * Convierte un SubMenuDTO a entidad SubMenu.
     * @param dto El SubMenuDTO.
     * @return La entidad SubMenu.
     */
    SubMenu toEntity(SubMenuDTO dto);

    /**
     * Convierte una lista de entidades SubMenu a una lista de SubMenuDtos.
     * @param entities La lista de entidades SubMenu.
     * @return The list of SubMenuDtos.
     */
    List<SubMenuDTO> toDtoList(List<SubMenu> entities);

    /**
     * Convierte una lista de SubMenuDtos a una lista de entidades SubMenu entities.
     * @param dtos The list of SubMenuDtos.
     * @return The list of SubMenu entities.
     */
    List<SubMenu> toEntityList(List<SubMenuDTO> dtos);
}