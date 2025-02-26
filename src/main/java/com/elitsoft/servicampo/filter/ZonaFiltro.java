package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ZonaFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 4870279123117042812L;

    private Long id;
    private String descripcionZona;

}