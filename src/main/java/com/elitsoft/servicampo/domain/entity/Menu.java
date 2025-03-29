package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.util.List;

/**
 *
 */
@Setter
@Getter
public class Menu {

    private Long id;
    private String nombre;
    private String icono;
    private List<Modulo> modulo;
}


