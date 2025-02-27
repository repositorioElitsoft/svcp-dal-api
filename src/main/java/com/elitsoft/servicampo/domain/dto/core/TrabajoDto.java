package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
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
@Builder
public class TrabajoDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -5994822367625238261L;

    private Long id;
    private String descripcionTrabajo;
    private List<TrabajoTarea> trabajoTareas;
}