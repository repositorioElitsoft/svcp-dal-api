package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.Comuna;
import com.elitsoft.servicampo.domain.entity.Region;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ProvinciaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -8978439306403287877L;

    private Long id;
    private String descripcionCiudad;
    private RegionDto region;
}