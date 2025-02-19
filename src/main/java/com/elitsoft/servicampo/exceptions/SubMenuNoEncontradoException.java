package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un SubMenu no es encontrado.
 */
public class SubMenuNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public SubMenuNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}