package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ClasificacionClienteDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -5677749071009987102L;

    private Long id;
    private String clasificacionClienteDesc;

}