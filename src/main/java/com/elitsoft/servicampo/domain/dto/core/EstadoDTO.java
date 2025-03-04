package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EstadoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7079198568797244414L;

    private Long id;
    private String descripcion;
}