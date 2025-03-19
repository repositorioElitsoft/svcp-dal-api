package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 */
@Setter
@Getter
public class Contrato implements Serializable {

    @Serial
    private static final long serialVersionUID = -2478427601849478930L;

    private Long id;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    private Estado estado;
    private Contacto contacto;
    private Cliente cliente;
   // private List<ContratoDetalle> contratosDetalle;

}