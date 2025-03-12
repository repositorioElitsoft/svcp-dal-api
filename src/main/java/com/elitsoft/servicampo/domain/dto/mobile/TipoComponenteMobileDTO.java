package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoComponenteMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5177865335836280694L;

    private Long id;
    private String nombre;
    private String descripcion;

}