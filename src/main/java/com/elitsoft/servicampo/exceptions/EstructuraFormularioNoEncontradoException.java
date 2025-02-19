package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un EstructuraFormulario no es encontrado.
 */
public class EstructuraFormularioNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public EstructuraFormularioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}