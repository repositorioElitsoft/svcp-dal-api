package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoDireccionMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4208198094009306917L;

    private Long id;
    private String descripcion;

}