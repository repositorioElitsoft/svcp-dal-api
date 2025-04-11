package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ContactoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -8368646882349043570L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private Character dv;
    private Long cliente;

}