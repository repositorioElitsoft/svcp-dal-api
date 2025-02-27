package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TipoServicioFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 2365159396208215022L;

    private Long id;
    private String descripcionTipoServicio;
}