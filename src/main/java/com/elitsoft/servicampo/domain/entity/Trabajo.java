package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 *
 */

public class Trabajo {

    private Long id;
    private String descripcionTrabajo;
    private List<TrabajoTarea> trabajoTareas;
}
