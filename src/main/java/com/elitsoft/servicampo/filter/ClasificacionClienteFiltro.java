package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ClasificacionClienteFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 5571421347158765196L;

    private Long id;
    private String clasificacionClienteDesc;

}