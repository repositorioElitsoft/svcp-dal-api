package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidad Carro que representa un carro en el sistema.
 * Relacionada con la tabla carros en la base de datos.
 */
@Setter
@Getter
public class Carro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6686386667323062434L;

    private Componente componente;
    private String patente;
    private String color;

}