package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.TipoDocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.entity.TipoDocumentoIdentificacion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad TipoDocumentoIdentificacion y DTO.
 */
@Mapper(componentModel = "spring")
public interface TipoDocumentoIdentificacionMapStruct {

    /**
     * Convierte un entidad TipoDocumentoIdentificacion a TipoDocumentoIdentificacionDTO.
     * @param entity La entidad TipoDocumentoIdentificacion.
     * @return El TipoDocumentoIdentificacionDTO.
     */
    TipoDocumentoIdentificacionDTO toDTO(TipoDocumentoIdentificacion entity);

    /**
     * Convierte un TipoDocumentoIdentificacionDTO a entidad TipoDocumentoIdentificacion.
     * @param dto El TipoDocumentoIdentificacionDTO.
     * @return La entidad TipoDocumentoIdentificacion.
     */
    TipoDocumentoIdentificacion toEntity(TipoDocumentoIdentificacionDTO dto);

    /**
     * Convierte una lista de entidades TipoDocumentoIdentificacion a una lista de TipoDocumentoIdentificacionDTOs.
     * @param entities La lista de entidades TipoDocumentoIdentificacion.
     * @return The list of TipoDocumentoIdentificacionDTOs.
     */
    List<TipoDocumentoIdentificacionDTO> toDTOList(List<TipoDocumentoIdentificacion> entities);

    /**
     * Convierte una lista de TipoDocumentoIdentificacionDTOs a una lista de entidades TipoDocumentoIdentificacion entities.
     * @param dto The list of TipoDocumentoIdentificacionDTOs.
     * @return The list of TipoDocumentoIdentificacion entities.
     */
    List<TipoDocumentoIdentificacion> toEntityList(List<TipoDocumentoIdentificacionDTO> dto);
}