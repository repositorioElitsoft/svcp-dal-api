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
public class AgrupacionComercial implements Serializable {

    @Serial
    private static final long serialVersionUID = -5332269590159958501L;

    private Long id;
    private String nombreGrupoComercial;

}