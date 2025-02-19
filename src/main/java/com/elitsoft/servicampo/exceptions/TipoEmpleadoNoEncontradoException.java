package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un TipoEmpleado no es encontrado.
 */
public class TipoEmpleadoNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public TipoEmpleadoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}