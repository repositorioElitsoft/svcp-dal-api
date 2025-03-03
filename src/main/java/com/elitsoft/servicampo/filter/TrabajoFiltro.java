package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class TrabajoFiltro implements Serializable {

    private Long id;
    private String descripcionTrabajo;


}