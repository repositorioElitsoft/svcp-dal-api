package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class TipoDocumentoIdentificacionFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 2421389551277137380L;

    private Long id;
    private String nombre;
    private String descripcion;

}