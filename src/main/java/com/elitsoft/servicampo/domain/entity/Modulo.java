package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 *
 */
public class Modulo {

    private Long id;
    private String nombreModulo;
    private String nombreFormulario;
    private Menu menu;
    private SubMenu submenu;
    private EstructuraFormulario estructuraFormulario;
}

