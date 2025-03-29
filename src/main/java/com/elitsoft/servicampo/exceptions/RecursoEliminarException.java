package com.elitsoft.servicampo.exceptions;


import lombok.Getter;

import java.io.Serial;

/**
 *
 */
@Getter
public class RecursoEliminarException extends Exception {
    @Serial
    private static final long serialVersionUID = 406317230484498707L; // Or a more specific exception type

    /**
     * -- GETTER --
     */
    private  String errorCode;

    /**
     * @param mensaje
     */
    public RecursoEliminarException(String mensaje) {
        super(mensaje);
    }

    public RecursoEliminarException(String errorCode, String mensaje) {
        super(mensaje);
        this.errorCode = errorCode;
    }

    /**
     * @param mensaje
     * @param causa
     */
    public RecursoEliminarException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public RecursoEliminarException(String errorCode, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.errorCode = errorCode;
    }
}
