package com.elitsoft.servicampo.exceptions;


import lombok.Getter;

import java.io.Serial;

/**
 *
 */
@Getter
public class RecursoDuplicadoException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -2549603805413106140L; // Or a more specific exception type

    /**
     * -- GETTER --
     */
    private  String errorCode;

    /**
     * @param mensaje
     */
    public RecursoDuplicadoException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param errorCode
     * @param mensaje
     */
    public RecursoDuplicadoException(String errorCode, String mensaje) {
        super(mensaje);
        this.errorCode = errorCode;
    }

    /**
     * @param mensaje
     * @param causa
     */
    public RecursoDuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * @param errorCode
     * @param mensaje
     * @param causa
     */
    public RecursoDuplicadoException(String errorCode, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.errorCode = errorCode;
    }

}
