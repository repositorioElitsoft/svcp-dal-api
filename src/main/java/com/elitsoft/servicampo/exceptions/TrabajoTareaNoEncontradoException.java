package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un TrabajoTarea no es encontrado.
 */
public class TrabajoTareaNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public TrabajoTareaNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}