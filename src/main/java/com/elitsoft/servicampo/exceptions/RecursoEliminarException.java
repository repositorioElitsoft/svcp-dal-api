package com.elitsoft.servicampo.exceptions;


/**
 *
 */
public class RecursoEliminarException extends IllegalArgumentException { // Or a more specific exception type

    /**
     * @param mensaje
     */
    public RecursoEliminarException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param mensaje
     * @param causa
     */
    public RecursoEliminarException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
