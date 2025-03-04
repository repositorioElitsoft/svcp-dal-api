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
public class TipoDocumentoIdentificacion implements Serializable {

    @Serial
    private static final long serialVersionUID = 8790729052086335465L;

    private Long id;
    private String nombre;
    private String descripcion;

}