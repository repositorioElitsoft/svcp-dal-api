package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CarroDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2003954866464336760L;

    private Long id;
    private String numero;
    private String modelo;
    private String caracteristica;
    private String proveedor;
}