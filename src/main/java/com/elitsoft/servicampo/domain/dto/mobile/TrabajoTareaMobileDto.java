package com.elitsoft.servicampo.domain.dto.mobile;

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
public class TrabajoTareaMobileDto implements Serializable  {

    @Serial
    private static final long serialVersionUID = 2184582570213676636L;

    private Long trabajoId;
    private Long tareaId;
    private Integer ordenEjecucionTarea;
    private Trabajo trabajo;
    private Tarea tarea;
}