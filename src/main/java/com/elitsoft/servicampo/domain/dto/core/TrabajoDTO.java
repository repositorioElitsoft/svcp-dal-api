package com.elitsoft.servicampo.domain.dto.core;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class TrabajoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5994822367625238261L;

    private Long id;
    private String descripcionTrabajo;
    private List<TrabajoTareaDTO> trabajoTareas;

}