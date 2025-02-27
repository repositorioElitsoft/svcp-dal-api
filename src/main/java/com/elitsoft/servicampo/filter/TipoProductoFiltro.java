package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TipoProductoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 7189548750089148402L;

    private Long id;
    private String descripcionTipoProducto;

}