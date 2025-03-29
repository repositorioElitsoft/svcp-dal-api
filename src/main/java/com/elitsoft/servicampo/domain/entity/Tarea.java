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
public class Tarea implements Serializable {

    @Serial
    private static final long serialVersionUID = -608862078311815497L;

    private Long id;
    private String descripcionTarea;
}


