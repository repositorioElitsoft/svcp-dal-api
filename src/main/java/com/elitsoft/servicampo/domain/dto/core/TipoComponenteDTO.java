package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoComponenteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8711617874266684509L;

    private Long id;
    private String nombre;
    private String descripcion;
}