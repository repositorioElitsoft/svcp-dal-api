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
public class Comuna implements Serializable {

    @Serial
    private static final long serialVersionUID = -4709640329454283191L;

    private Long id;
    private String descripcionComuna;
    private Provincia provincia;


}