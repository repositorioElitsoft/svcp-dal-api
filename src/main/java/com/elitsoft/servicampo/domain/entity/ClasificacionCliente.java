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
public class ClasificacionCliente implements Serializable {

    @Serial
    private static final long serialVersionUID = -8486419919187548938L;

    private Long id;
    private String clasificacionClienteDesc;

}