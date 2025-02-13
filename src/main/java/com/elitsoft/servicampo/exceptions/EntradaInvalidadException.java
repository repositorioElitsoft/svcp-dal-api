package com.elitsoft.servicampo.exceptions;


/**
 *
 */
public class EntradaInvalidadException extends IllegalArgumentException { // Or a more specific exception type

    /**
     * @param mensaje
     */
    public EntradaInvalidadException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param mensaje
     * @param causa
     */
    public EntradaInvalidadException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
