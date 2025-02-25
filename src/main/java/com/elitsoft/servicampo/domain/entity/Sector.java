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
public class Sector implements Serializable {

    @Serial
    private static final long serialVersionUID = -6161298292959899469L;
    private Long id;
    private String descripcionSector;
    private Zona zona;



}