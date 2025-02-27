package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoServicioDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6599542277234982122L;

    private Long id;
    private String descripcionTipoServicio;
}