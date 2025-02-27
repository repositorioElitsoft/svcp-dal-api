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
public class TipoServicio implements Serializable {

    @Serial
    private static final long serialVersionUID = 791756328471613639L;

    private Long id;
    private String descripcionTipoServicio;

}