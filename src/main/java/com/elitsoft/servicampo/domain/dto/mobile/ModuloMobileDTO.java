package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

@Setter
@Getter
public class ModuloMobileDTO {

    private Long id;
    private String nombreModulo;
    private String nombreFormulario;
    private Long menuId;
    private Long subMenuId;
    private Long estructuraFormularioId;
}