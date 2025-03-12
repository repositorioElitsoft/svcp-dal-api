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
public class ServicioTrabajo implements Serializable {

    @Serial
    private static final long serialVersionUID = 9172203145781336157L;

    private Servicio servicio;
    private Trabajo trabajo;
    private Integer secuencia;

}