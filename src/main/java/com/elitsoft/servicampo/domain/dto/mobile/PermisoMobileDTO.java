package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

@Setter
@Getter
public class PermisoMobileDTO {

    private Long id;
    private String nombre;
    private Long moduloId;
    private Long roleId;
}