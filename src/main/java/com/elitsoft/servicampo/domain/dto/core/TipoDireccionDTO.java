package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoDireccionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3197200596874723227L;

    private Long id;
    private String descripcion;
}