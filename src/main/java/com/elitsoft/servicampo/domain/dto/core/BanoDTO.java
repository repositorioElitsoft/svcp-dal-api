package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class BanoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7678663246575623188L;

    private ComponenteDTO componente;
    private String marca;
    private String colorPuerta;
    private String colorPared;
}