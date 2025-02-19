package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Modulo no es encontrado.
 */
public class ModuloNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public ModuloNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}