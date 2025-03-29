package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;


/**
 *
 */
@Setter
@Getter
public class Permiso {

    private Long id;
    private String nombre;
    private Modulo modulo;
    private Role role;
}

