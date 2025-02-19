package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.MenuDto;
import com.elitsoft.servicampo.domain.entity.Menu;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Menu y DTO.
 */
@Mapper(componentModel = "spring")
public interface MenuMapStruct {

    /**
     * Convierte un entidad Menu a MenuDto.
     * @param entity La entidad Menu.
     * @return El MenuDto.
     */
    MenuDto toDto(Menu entity);

    /**
     * Convierte un MenuDto a entidad Menu.
     * @param dto El MenuDto.
     * @return La entidad Menu.
     */
    Menu toEntity(MenuDto dto);

    /**
     * Convierte una lista de entidades Menu a una lista de MenuDtos.
     * @param entities La lista de entidades Menu.
     * @return The list of MenuDtos.
     */
    List<MenuDto> toDtoList(List<Menu> entities);

    /**
     * Convierte una lista de MenuDtos a una lista de entidades Menu entities.
     * @param dtos The list of MenuDtos.
     * @return The list of Menu entities.
     */
    List<Menu> toEntityList(List<MenuDto> dtos);
}