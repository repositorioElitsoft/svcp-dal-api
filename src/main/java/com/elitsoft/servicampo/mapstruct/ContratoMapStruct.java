package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ContratoDTO;
import com.elitsoft.servicampo.domain.entity.Contrato;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Contrato y DTO.
 */
@Mapper(componentModel = "spring")
public interface ContratoMapStruct {

    /**
     * Convierte un entidad Contrato a ContratoDTO.
     * @param entity La entidad Contrato.
     * @return El ContratoDTO.
     */
    ContratoDTO toDTO(Contrato entity);

    /**
     * Convierte un ContratoDTO a entidad Contrato.
     * @param dto El ContratoDTO.
     * @return La entidad Contrato.
     */
    Contrato toEntity(ContratoDTO dto);

    /**
     * Convierte una lista de entidades Contrato a una lista de ContratoDTOs.
     * @param entities La lista de entidades Contrato.
     * @return The list of ContratoDTOs.
     */
    List<ContratoDTO> toDTOList(List<Contrato> entities);

    /**
     * Convierte una lista de ContratoDTOs a una lista de entidades Contrato entities.
     * @param dto The list of ContratoDTOs.
     * @return The list of Contrato entities.
     */
    List<Contrato> toEntityList(List<ContratoDTO> dto);
}