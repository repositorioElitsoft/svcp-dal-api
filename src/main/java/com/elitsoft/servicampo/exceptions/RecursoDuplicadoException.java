package com.elitsoft.servicampo.exceptions;


import java.io.Serial;

/**
 *
 */
public class RecursoDuplicadoException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -2549603805413106140L; // Or a more specific exception type

    private  String codigoError;

    /**
     * @param mensaje
     */
    public RecursoDuplicadoException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param codigoError
     * @param mensaje
     */
    public RecursoDuplicadoException(String codigoError, String mensaje) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    /**
     * @param mensaje
     * @param causa
     */
    public RecursoDuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * @param codigoError
     * @param mensaje
     * @param causa
     */
    public RecursoDuplicadoException(String codigoError, String mensaje, Throwable causa) {
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
