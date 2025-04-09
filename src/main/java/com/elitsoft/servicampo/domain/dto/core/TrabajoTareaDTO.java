package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class TrabajoTareaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7072626042145555893L;

    private TrabajoDTO trabajo;
    private TareaDTO tarea;
    private Integer ordenEjecucionTarea;
}