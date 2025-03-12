package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class EstadoProductoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 4691625010080463065L;

    private Long id;
    private String descripcion;

}