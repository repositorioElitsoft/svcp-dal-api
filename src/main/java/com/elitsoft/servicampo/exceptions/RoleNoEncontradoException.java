package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Role no es encontrado.
 */
public class RoleNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public RoleNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}