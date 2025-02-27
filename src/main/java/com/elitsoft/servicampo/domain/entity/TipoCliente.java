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
public class TipoCliente implements Serializable {


    @Serial
    private static final long serialVersionUID = 2993038309474439637L;

    private Long id;
    private String nombre;



}