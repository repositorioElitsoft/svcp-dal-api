package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Demo no es encontrado.
 */
public class RecursoNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}