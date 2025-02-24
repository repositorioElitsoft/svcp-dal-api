package com.elitsoft.servicampo.exceptions;


/**
 *
 */
public class RecursoDuplicadoException extends IllegalArgumentException { // Or a more specific exception type

    /**
     * @param mensaje
     */
    public RecursoDuplicadoException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param mensaje
     * @param causa
     */
    public RecursoDuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
