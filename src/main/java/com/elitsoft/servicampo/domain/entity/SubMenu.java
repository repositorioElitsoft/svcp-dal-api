package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 */
@Setter
@Getter
public class SubMenu {

    private Long id;
    private String nombre;
    private Menu menu;
    private List<Modulo> modulo;
}

