package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class CarroFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6636480672303969332L;

    //filtros
    private Long id;
    private String numero;
    private String modelo;

    //ordenamiento
    private String caracteristica;
    private String proveedor;
}