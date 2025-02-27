package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 *
 */
public class TrabajoTarea  implements Serializable {

    @Serial
    private static final long serialVersionUID = 8630087261893726669L;

    private Long trabajoId;
    private Long tareaId;
    private Integer ordenEjecucionTarea;
    private Trabajo trabajo;
    private Tarea tarea;
}
