package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 *
 */
public class TrabajoTarea {

    private Long trabajoId;
    private Long tareaId;
    private Integer ordenEjecucionTarea;
    private Trabajo trabajo;
    private Tarea tarea;
}
