package com.elitsoft.servicampo.domain.dto.mobile;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ServicioTrabajoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4114398594154558965L;

    private ServicioMobileDTO servicio;
    private TrabajoMobileDTO trabajo;
    private Integer secuencia;

}