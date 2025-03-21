package com.elitsoft.servicampo.domain.dto.mobile;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ServicioMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7480719467676845502L;

    private Long id;
    private String descripcion;
    private EstadoMobileDTO estado;
    private TipoServicioMobileDTO tipoServicio;

}