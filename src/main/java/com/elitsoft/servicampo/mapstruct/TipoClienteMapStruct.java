package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoClienteDTO;
import com.elitsoft.servicampo.domain.entity.TipoCliente;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoCliente y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoClienteMapStruct {

    /**
     * Convierte un entidad TipoCliente a TipoClienteDTO.
     * @param entity La entidad TipoCliente.
     * @return El TipoClienteDTO.
     */
    TipoClienteDTO toDTO(TipoCliente entity);

    /**
     * Convierte un TipoClienteDTO a entidad TipoCliente.
     * @param dto El TipoClienteDTO.
     * @return La entidad TipoCliente.
     */
    TipoCliente toEntity(TipoClienteDTO dto);

    /**
     * Convierte una lista de entidades TipoCliente a una lista de TipoClienteDtos.
     * @param entities La lista de entidades TipoCliente.
     * @return The list of TipoClienteDtos.
     */
    List<TipoClienteDTO> toDTOList(List<TipoCliente> entities);

    /**
     * Convierte una lista de TipoClienteDtos a una lista de entidades TipoCliente entities.
     * @param dtos The list of TipoClienteDtos.
     * @return The list of TipoCliente entities.
     */
    List<TipoCliente> toEntityList(List<TipoClienteDTO> dtos);
}