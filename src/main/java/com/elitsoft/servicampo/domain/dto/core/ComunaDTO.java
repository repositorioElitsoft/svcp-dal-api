package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ComunaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7218641512197564385L;

    private Long id;
    private String descripcionComuna;
    private ProvinciaDTO provincia;
}