package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleDTO;
import com.elitsoft.servicampo.domain.entity.ContratoDetalle;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad ContratoDetalle y DTO.
 */
@Mapper(componentModel = "spring")
public interface ContratoDetalleMapStruct {

    /**
     * Convierte un entidad ContratoDetalle a ContratoDetalleDTO.
     * @param entity La entidad ContratoDetalle.
     * @return El ContratoDetalleDTO.
     */
    ContratoDetalleDTO toDTO(ContratoDetalle entity);

    /**
     * Convierte un ContratoDetalleDTO a entidad ContratoDetalle.
     * @param dto El ContratoDetalleDTO.
     * @return La entidad ContratoDetalle.
     */
    ContratoDetalle toEntity(ContratoDetalleDTO dto);

    /**
     * Convierte una lista de entidades ContratoDetalle a una lista de ContratoDetalleDTOs.
     * @param entities La lista de entidades ContratoDetalle.
     * @return The list of ContratoDetalleDTOs.
     */
    List<ContratoDetalleDTO> toDTOList(List<ContratoDetalle> entities);

    /**
     * Convierte una lista de ContratoDetalleDTOs a una lista de entidades ContratoDetalle entities.
     * @param dto The list of ContratoDetalleDTOs.
     * @return The list of ContratoDetalle entities.
     */
    List<ContratoDetalle> toEntityList(List<ContratoDetalleDTO> dto);
}