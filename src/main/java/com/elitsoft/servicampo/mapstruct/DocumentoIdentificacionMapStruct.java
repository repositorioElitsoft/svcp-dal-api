package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.DocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.entity.DocumentoIdentificacion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad DocumentoIdentificacion y DTO.
 */
@Mapper(componentModel = "spring")
public interface DocumentoIdentificacionMapStruct {

    /**
     * Convierte un entidad DocumentoIdentificacion a DocumentoIdentificacionDTO.
     * @param entity La entidad DocumentoIdentificacion.
     * @return El DocumentoIdentificacionDTO.
     */
    DocumentoIdentificacionDTO toDTO(DocumentoIdentificacion entity);

    /**
     * Convierte un DocumentoIdentificacionDTO a entidad DocumentoIdentificacion.
     * @param dto El DocumentoIdentificacionDTO.
     * @return La entidad DocumentoIdentificacion.
     */
    DocumentoIdentificacion toEntity(DocumentoIdentificacionDTO dto);

    /**
     * Convierte una lista de entidades DocumentoIdentificacion a una lista de DocumentoIdentificacionDTOs.
     * @param entities La lista de entidades DocumentoIdentificacion.
     * @return The list of DocumentoIdentificacionDTOs.
     */
    List<DocumentoIdentificacionDTO> toDTOList(List<DocumentoIdentificacion> entities);

    /**
     * Convierte una lista de DocumentoIdentificacionDTOs a una lista de entidades DocumentoIdentificacion entities.
     * @param dto The list of DocumentoIdentificacionDTOs.
     * @return The list of DocumentoIdentificacion entities.
     */
    List<DocumentoIdentificacion> toEntityList(List<DocumentoIdentificacionDTO> dto);
}