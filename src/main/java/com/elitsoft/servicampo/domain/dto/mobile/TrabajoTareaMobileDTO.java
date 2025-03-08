package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.TareaDTO;
import com.elitsoft.servicampo.domain.dto.core.TrabajoDTO;
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
public class TrabajoTareaMobileDTO implements Serializable  {

    @Serial
    private static final long serialVersionUID = 2184582570213676636L;

    private Long trabajoId;
    private Long tareaId;
    private Integer ordenEjecucionTarea;
    private TrabajoDTO trabajo;
    private TareaDTO tarea;
}