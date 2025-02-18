package com.elitsoft.#app_name#.exceptions;

/**
 * Exception producida cuando un #Base# no es encontrado.
 */
public class #Base#NoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public #Base#NoEncontradoException(String mensaje) {
        super(mensaje);
    }
}