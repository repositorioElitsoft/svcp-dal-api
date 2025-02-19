package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuloMobileDto {

    private Long id;
    private String nombreModulo;
    private String nombreFormulario;
    private Long menuId;
    private Long subMenuId;
    private Long estructuraFormularioId;
}