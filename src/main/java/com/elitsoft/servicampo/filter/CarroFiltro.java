package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase filtro para la entidad Carro.
 * Contiene los campos por los que se puede filtrar en consultas.
 */
@Data
public class CarroFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6636480672303969332L;

    private Long componenteId;
    private String patente;
    private String color;
}