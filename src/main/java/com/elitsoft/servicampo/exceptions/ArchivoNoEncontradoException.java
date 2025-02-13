package com.elitsoft.servicampo.exceptions;

/**
 *
 */
public class ArchivoNoEncontradoException extends RuntimeException { // Or extend a more appropriate exception

    /**
     * @param mensaje
     */
    public ArchivoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param mensaje
     * @param causa
     */
    public ArchivoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}