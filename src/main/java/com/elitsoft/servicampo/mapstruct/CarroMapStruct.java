package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.CarroDTO;
import com.elitsoft.servicampo.domain.entity.Carro;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Carro y DTO.
 */
@Mapper(componentModel = "spring")
public interface CarroMapStruct {

    /**
     * Convierte un entidad Carro a CarroDTO.
     * @param entity La entidad Carro.
     * @return El CarroDTO.
     */
    CarroDTO toDTO(Carro entity);

    /**
     * Convierte un CarroDTO a entidad Carro.
     * @param dto El CarroDTO.
     * @return La entidad Carro.
     */
    Carro toEntity(CarroDTO dto);

    /**
     * Convierte una lista de entidades Carro a una lista de CarroDTOs.
     * @param entities La lista de entidades Carro.
     * @return The list of CarroDTOs.
     */
    List<CarroDTO> toDTOList(List<Carro> entities);

    /**
     * Convierte una lista de CarroDTOs a una lista de entidades Carro entities.
     * @param dto The list of CarroDTOs.
     * @return The list of Carro entities.
     */
    List<Carro> toEntityList(List<CarroDTO> dto);
}