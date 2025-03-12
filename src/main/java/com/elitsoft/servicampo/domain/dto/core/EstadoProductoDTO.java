package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EstadoProductoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1723604173876772741L;

    private Long id;
    private String descripcion;
}