package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class TipoEmpleadoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -578488566580017789L;

    private Long id;
    private String descripcionTipoEmpleado;
}