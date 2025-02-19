package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Trabajo no es encontrado.
 */
public class TrabajoNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public TrabajoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}