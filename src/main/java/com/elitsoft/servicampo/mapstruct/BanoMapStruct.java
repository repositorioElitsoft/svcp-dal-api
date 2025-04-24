package com.elitsoft.servicampo.mapstruct;

import com.elitsoft.servicampo.domain.dto.core.BanoDTO;
import com.elitsoft.servicampo.domain.entity.Bano;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz MapStruct Mapper para la entidad Bano y DTO.
 */
@Mapper(componentModel = "spring")
public interface BanoMapStruct {

    /**
     * Convierte un entidad Bano a BanoDTO.
     * @param entity La entidad Bano.
     * @return El BanoDTO.
     */
    BanoDTO toDTO(Bano entity);

    /**
     * Convierte un BanoDTO a entidad Bano.
     * @param dto El BanoDTO.
     * @return La entidad Bano.
     */
    Bano toEntity(BanoDTO dto);

    /**
     * Convierte una lista de entidades Bano a una lista de BanoDTOs.
     * @param entities lista de entidades Bano.
     * @return lista de BanoDTOs.
     */
    List<BanoDTO> toDTOList(List<Bano> entities);

    /**
     * Convierte una lista de BanoDTOs a una lista de entidades Bano.
     * @param dto lista de BanoDTOs.
     * @return lista de entidades Bano.
     */
    List<Bano> toEntityList(List<BanoDTO> dto);
}