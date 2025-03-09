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
public class TipoDireccion implements Serializable {

    @Serial
    private static final long serialVersionUID = -3453330896366452269L;

    private Long id;
    private String descripcion;

}