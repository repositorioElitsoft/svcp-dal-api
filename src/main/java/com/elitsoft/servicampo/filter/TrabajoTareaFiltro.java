package com.elitsoft.servicampo.filter;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TrabajoTareaFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -8788161734318619625L;

    private Long trabajoId;
    private Long tareaId;
    private Integer ordenEjecucionTarea;

}