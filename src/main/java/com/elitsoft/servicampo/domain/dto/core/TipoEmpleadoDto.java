package com.elitsoft.servicampo.domain.dto.core;

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
public class TipoEmpleadoDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7251846584199656552L;

    private Long id;
    private String descripcionTipoEmpleado;
}