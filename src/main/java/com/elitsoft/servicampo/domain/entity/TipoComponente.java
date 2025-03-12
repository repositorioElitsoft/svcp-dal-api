package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */
@Setter
@Getter
public class TipoComponente implements Serializable {

    @Serial
    private static final long serialVersionUID = 4670563198102984170L;

    private Long id;
    private String nombre;
    private String descripcion;

}