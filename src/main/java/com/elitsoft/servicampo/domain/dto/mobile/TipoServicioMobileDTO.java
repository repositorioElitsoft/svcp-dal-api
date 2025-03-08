package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoServicioMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7731201901214980335L;

    private Long id;
    private String descripcionTipoServicio;

}