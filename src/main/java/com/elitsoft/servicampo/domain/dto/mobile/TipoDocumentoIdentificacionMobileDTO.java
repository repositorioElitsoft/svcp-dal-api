package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoDocumentoIdentificacionMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3190712549148953430L;

    private Long id;
    private String nombre;
    private String descripcion;

}