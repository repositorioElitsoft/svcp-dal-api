package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class SegmentacionClienteDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3050391477505621312L;

    private Long id;
    private String descripcion;
}