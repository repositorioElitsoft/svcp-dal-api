package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class EstadoComponenteFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 451178550014068782L;

    private Long id;
    private String nombre;

}