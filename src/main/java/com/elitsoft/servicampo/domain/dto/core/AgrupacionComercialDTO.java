package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AgrupacionComercialDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3269070301469473411L;

    private Long id;
    private String nombreGrupoComercial;
}