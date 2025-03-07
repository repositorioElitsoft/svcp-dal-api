package com.elitsoft.servicampo.exceptions;


import java.io.Serial;

/**
 *
 */
public class EntradaInvalidadException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -1656055939158112685L; // Or a more specific exception type

    private  String codigoError;

    /**
     * @param mensaje
     */
    public EntradaInvalidadException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param codigoError
     * @param mensaje
     */
    public EntradaInvalidadException(String codigoError, String mensaje) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    /**
     * @param mensaje
     * @param causa
     */
    public EntradaInvalidadException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * @param codigoError
     * @param mensaje
     * @param causa
     */
    public EntradaInvalidadException(String codigoError, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
    }

    /**
     * @return
     */
    public String getCodigoError() {
        return this.codigoError;
    }
}
