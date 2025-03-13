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
public class Carro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6686386667323062434L;

    private Long id;
    private String numero;
    private String modelo;
    private String caracteristica;
    private String proveedor;

}