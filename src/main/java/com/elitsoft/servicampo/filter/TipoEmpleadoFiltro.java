package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TipoEmpleadoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 7798804614165097416L;

    private Long id;
    private String descripcionTipoEmpleado;

}