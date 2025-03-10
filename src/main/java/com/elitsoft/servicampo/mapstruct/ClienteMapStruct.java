package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ClienteDTO;
import com.elitsoft.servicampo.domain.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Cliente y DTO.
 */
@Mapper(componentModel = "spring")
public interface ClienteMapStruct {

    /**
     * Convierte un entidad Cliente a ClienteDTO.
     * @param entity La entidad Cliente.
     * @return El ClienteDTO.
     */
    ClienteDTO toDTO(Cliente entity);

    /**
     * Convierte un ClienteDTO a entidad Cliente.
     * @param dto El ClienteDTO.
     * @return La entidad Cliente.
     */
    Cliente toEntity(ClienteDTO dto);

    /**
     * Convierte una lista de entidades Cliente a una lista de ClienteDTOs.
     * @param entities La lista de entidades Cliente.
     * @return The list of ClienteDTOs.
     */
    List<ClienteDTO> toDTOList(List<Cliente> entities);

    /**
     * Convierte una lista de ClienteDTOs a una lista de entidades Cliente entities.
     * @param dto The list of ClienteDTOs.
     * @return The list of Cliente entities.
     */
    List<Cliente> toEntityList(List<ClienteDTO> dto);
}