package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Setter
@Getter
public class Trabajo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4340416980032460063L;

    private Long id;
    private String descripcionTrabajo;
    private List<TrabajoTarea> trabajoTareas;
}
