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
public class Servicio implements Serializable {

    @Serial
    private static final long serialVersionUID = 3602594254673056399L;

    private Long id;
    private String descripcion;
    private Estado estado;
    private TipoServicio tipoServicio;
    //private List<ServicioTrabajo> servicioTrabajos;

}