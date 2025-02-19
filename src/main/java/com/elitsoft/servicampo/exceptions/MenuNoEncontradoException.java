package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Menu no es encontrado.
 */
public class MenuNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public MenuNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}