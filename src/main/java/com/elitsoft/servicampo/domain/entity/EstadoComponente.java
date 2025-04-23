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
public class EstadoComponente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1766626435076179680L;

    private Long id;
    private String nombre;


}