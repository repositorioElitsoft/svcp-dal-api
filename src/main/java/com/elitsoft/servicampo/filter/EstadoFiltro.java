package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class EstadoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 7196947554425163607L;

    private Long id;
    private String descripcion;

}