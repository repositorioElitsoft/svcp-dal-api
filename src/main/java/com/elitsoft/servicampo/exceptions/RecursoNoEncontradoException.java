package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Demo no es encontrado.
 */
public class RecursoNoEncontradoException extends Exception {

    private  String codigoError;

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    /**
     *
     * @param codigoError
     * @param mensaje
     */
    public RecursoNoEncontradoException(String codigoError,String mensaje) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    /**
     * @return
     */
    public String getCodigoError() {
        return this.codigoError;
    }
}