package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModuloDTO {

    private Long id;
    private String nombreModulo;
    private String nombreFormulario;
    private Long menuId;
    private Long subMenuId;
    private Long estructuraFormularioId;
}