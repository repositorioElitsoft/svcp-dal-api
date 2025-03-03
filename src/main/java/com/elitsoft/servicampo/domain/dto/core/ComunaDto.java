package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.Provincia;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ComunaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7218641512197564385L;

    private Long id;
    private String descripcionComuna;
    private ProvinciaDto provincia;
}