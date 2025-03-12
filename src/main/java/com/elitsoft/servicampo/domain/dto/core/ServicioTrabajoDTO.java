package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ServicioTrabajoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 811688815744756851L;

    private ServicioDTO servicio;
    private TrabajoDTO trabajo;
    private Integer secuencia;
}