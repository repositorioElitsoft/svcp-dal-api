package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DireccionEmpleadoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4327488847372662959L;

    private Long id;
    private EstadoDTO estado;
    private ComunaDto comuna;
    private String calle;
    private String numeracion;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String referencia;
    private EmpleadoDTO empleado;
}