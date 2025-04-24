package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase filtro para la entidad Bano.
 * Contiene los campos por los que se puede filtrar en consultas.
 */
@Data
public class BanoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -3801729242996900104L;

    private Long componenteId;
    private String marca;
    private String colorPuerta;
    private String colorPared;
}