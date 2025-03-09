package com.elitsoft.servicampo.exceptions;


import lombok.Getter;

import java.io.Serial;

/**
 *
 */
@Getter
public class EntradaInvalidadException extends IllegalArgumentException {
    @Serial
    private static final long serialVersionUID = -1656055939158112685L; // Or a more specific exception type

    /**
     * -- GETTER --
     *
     * @return
     */
    private  String errorCode;

    /**
     * @param mensaje
     */
    public EntradaInvalidadException(String mensaje) {
        super(mensaje);
    }

    /**
     * @param errorCode
     * @param mensaje
     */
    public EntradaInvalidadException(String errorCode, String mensaje) {
        super(mensaje);
        this.errorCode = errorCode;
    }

    /**
     * @param mensaje
     * @param causa
     */
    public EntradaInvalidadException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * @param errorCode
     * @param mensaje
     * @param causa
     */
    public EntradaInvalidadException(String errorCode, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.errorCode = errorCode;
    }

}
