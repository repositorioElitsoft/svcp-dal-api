package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Tarea no es encontrado.
 */
public class TareaNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public TareaNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}