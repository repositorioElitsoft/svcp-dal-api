package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class TipoDireccionFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 2694544805854820443L;

    private Long id;
    private String descripcion;
}