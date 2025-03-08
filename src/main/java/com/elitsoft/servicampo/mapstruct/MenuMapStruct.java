package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.MenuDTO;
import com.elitsoft.servicampo.domain.entity.Menu;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Menu y DTO.
 */
@Mapper(componentModel = "spring")
public interface MenuMapStruct {

    /**
     * Convierte un entidad Menu a MenuDTO.
     * @param entity La entidad Menu.
     * @return El MenuDTO.
     */
    MenuDTO toDto(Menu entity);

    /**
     * Convierte un MenuDTO a entidad Menu.
     * @param dto El MenuDTO.
     * @return La entidad Menu.
     */
    Menu toEntity(MenuDTO dto);

    /**
     * Convierte una lista de entidades Menu a una lista de MenuDtos.
     * @param entities La lista de entidades Menu.
     * @return The list of MenuDtos.
     */
    List<MenuDTO> toDtoList(List<Menu> entities);

    /**
     * Convierte una lista de MenuDtos a una lista de entidades Menu entities.
     * @param dtos The list of MenuDtos.
     * @return The list of Menu entities.
     */
    List<Menu> toEntityList(List<MenuDTO> dtos);
}