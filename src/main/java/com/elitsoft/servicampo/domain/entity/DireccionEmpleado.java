package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */
@Setter
@Getter
public class DireccionEmpleado implements Serializable {

    @Serial
    private static final long serialVersionUID = 7770705708803265226L;

    private Long id;
    private Estado estado;
    private Comuna comuna;
    private String calle;
    private String numeracion;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String referencia;
    private Empleado empleado;
}