package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidad Bano que representa un baño en el sistema.
 * Relacionada con la tabla banos en la base de datos.
 */
@Setter
@Getter
public class Bano implements Serializable {

    @Serial
    private static final long serialVersionUID = -6417007007807008847L;

    private Componente componente;
    private String marca;
    private String colorPuerta;
    private String colorPared;

}