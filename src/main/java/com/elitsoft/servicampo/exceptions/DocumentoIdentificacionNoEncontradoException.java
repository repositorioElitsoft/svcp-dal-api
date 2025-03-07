package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un Demo no es encontrado.
 */
public class DocumentoIdentificacionNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public DocumentoIdentificacionNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}