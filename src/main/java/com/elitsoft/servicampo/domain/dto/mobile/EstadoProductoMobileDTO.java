package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EstadoProductoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5208206529787564624L;

    private Long id;
    private String descripcion;

}