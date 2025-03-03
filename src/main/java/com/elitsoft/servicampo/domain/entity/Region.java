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
public class Region implements Serializable {


    @Serial
    private static final long serialVersionUID = -4639356964511483288L;

    private Long id;
    private String descripcionRegion;
    private Pais pais;
    private List<Provincia> provincias;

}