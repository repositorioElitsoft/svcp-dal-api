package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Setter
@Getter
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 4822150395035747894L;

    private Long id;
    private String nombreRol;
    private List<Permiso> permisos;
}
