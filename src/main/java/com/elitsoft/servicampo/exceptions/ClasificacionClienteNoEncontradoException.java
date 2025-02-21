package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un ClasificacionCliente no es encontrado.
 */
public class ClasificacionClienteNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public ClasificacionClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}