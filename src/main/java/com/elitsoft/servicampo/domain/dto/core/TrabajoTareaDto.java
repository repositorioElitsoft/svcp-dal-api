package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrabajoTareaDto {

    private Long trabajoId;
    private Long tareaId;
    private Integer ordenEjecucionTarea;
}