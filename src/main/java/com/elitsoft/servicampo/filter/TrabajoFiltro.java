package com.elitsoft.servicampo.filter;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
public class TrabajoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 8820477476615168421L;

    private Long id;
    private String descripcionTrabajo;


}