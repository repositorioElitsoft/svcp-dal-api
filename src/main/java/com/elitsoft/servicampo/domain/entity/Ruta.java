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
public class Ruta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1748283417500250959L;

    private Long id;
    private String descripcion;

}