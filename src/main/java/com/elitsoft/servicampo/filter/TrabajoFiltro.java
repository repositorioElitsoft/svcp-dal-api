package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class TrabajoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 604001424577026403L;

    private Long id;
    private String descripcionTrabajo;
    private String descripcionTarea;

    //ordenamiento
    private Integer ordenEjecucionTarea;


}