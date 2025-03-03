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
public class Provincia implements Serializable {

    @Serial
    private static final long serialVersionUID = -3948232266876104022L;

    private Long id;
    private String descripcionCiudad;
    private Region region;
    private List<Comuna> comunas;

}