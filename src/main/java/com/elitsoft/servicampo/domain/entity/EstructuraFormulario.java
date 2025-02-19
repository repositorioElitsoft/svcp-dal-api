package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 *
 */
public class EstructuraFormulario {

    private Long id;
    private String path;
    private String nombreFormulario;
    private String table;
    private String campo;
    private String maxLong;
    private String tipoCampo;
    private String titulo;
    private String boton;
    private Long largo;
    private Boolean editar;
    private Boolean visible;
    private Long orden;
}


