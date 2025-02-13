package com.elitsoft.servicampo.exceptions;

/**
 *
 */
public class BaseDatosException extends Exception{

    /**
     * @param mensaje
     */
    public BaseDatosException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param mensaje
     * @param causa
     */
    public BaseDatosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
