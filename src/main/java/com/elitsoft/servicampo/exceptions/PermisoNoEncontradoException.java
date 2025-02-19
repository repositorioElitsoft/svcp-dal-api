package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Permiso no es encontrado.
 */
public class PermisoNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public PermisoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}