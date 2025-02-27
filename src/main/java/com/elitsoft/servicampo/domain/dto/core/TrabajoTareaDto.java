package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.Tarea;
import com.elitsoft.servicampo.domain.entity.Trabajo;
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
public class TrabajoTareaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -7072626042145555893L;

    private Long trabajoId;
    private Long tareaId;
    private Integer ordenEjecucionTarea;
    private Trabajo trabajo;
    private Tarea tarea;
}