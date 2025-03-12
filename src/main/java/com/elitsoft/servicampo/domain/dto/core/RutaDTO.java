package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class RutaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2732358652900269756L;

    private Long id;
    private String descripcion;
}