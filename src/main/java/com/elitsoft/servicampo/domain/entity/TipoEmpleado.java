package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */
@Setter
@Getter
public class TipoEmpleado implements Serializable {

    @Serial
    private static final long serialVersionUID = -8783059998962142996L;

    private Long id;
    private String descripcionTipoEmpleado;
}


