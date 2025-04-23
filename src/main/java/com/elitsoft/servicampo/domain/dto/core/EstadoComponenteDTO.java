package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EstadoComponenteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2224376281084128860L;

    private Long id;
    private String nombre;
}