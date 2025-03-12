package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class TipoComponenteFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 2813646943994924224L;

    //filtros
    private Long id;
    private String nombre;

    //ordenamiento
    private String descripcion;

}