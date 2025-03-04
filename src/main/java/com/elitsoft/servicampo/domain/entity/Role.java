package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 *
 */
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 4822150395035747894L;

    private Long id;
    private String nombreRol;
    private List<Permiso> permisos;
}
