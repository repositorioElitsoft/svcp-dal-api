package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Estado no es encontrado.
 */
public class EstadoNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public EstadoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}