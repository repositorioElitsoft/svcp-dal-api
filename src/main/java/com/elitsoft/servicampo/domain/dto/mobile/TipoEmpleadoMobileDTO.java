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
public class TipoEmpleadoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -578488566580017789L;

    private Long id;
    private String descripcionTipoEmpleado;
}