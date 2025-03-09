package com.elitsoft.servicampo.domain.dto.mobile;

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
    private TrabajoMobileDTO trabajo;
    private TareaMobileDTO tarea;
}