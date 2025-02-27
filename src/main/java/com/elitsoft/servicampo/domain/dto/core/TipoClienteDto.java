package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoClienteDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4176676288744218838L;

    private Long id;
    private String nombre;
}