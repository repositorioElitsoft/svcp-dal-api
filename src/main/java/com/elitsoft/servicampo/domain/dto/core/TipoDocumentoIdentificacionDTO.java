package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoDocumentoIdentificacionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 513623204863379974L;

    private Long id;
    private String nombre;
    private String descripcion;
}