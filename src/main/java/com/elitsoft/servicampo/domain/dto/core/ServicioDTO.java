package com.elitsoft.servicampo.domain.dto.core;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ServicioDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8760196368081057708L;

    private Long id;
    private String descripcion;
    private EstadoDTO estado;
    private TipoServicioDTO tipoServicio;

}