package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class RutaFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -754030782303727529L;

    private Long id;
    private String descripcion;

}