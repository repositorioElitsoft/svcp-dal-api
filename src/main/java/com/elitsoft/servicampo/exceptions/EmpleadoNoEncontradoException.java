package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Empleado no es encontrado.
 */
public class EmpleadoNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public EmpleadoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}