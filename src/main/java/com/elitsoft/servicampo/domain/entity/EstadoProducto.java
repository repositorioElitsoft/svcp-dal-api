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
public class EstadoProducto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6366515723513461356L;

    private Long id;
    private String descripcion;

}