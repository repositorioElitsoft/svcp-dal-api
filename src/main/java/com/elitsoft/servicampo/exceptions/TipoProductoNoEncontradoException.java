package com.elitsoft.servicampo.exceptions;

/**
 * Exception producida cuando un TipoProducto no es encontrado.
 */
public class TipoProductoNoEncontradoException extends Exception {

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public TipoProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}