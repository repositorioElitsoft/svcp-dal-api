package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Setter
@Getter
public class Pais implements Serializable {

    @Serial
    private static final long serialVersionUID = -3729975013768405292L;

    private Long id;
    private String descripcionPais;
    private List<Region> regiones;

}