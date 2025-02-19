package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Zona no es encontrado.
 */
public class ZonaNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public ZonaNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}