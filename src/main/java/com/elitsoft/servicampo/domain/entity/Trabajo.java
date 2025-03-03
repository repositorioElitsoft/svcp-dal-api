package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 *
 */

public class Trabajo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4340416980032460063L;

    private Long id;
    private String descripcionTrabajo;
    private List<TrabajoTarea> trabajoTareas;
}
