package com.elitsoft.servicampo.exceptions;

import lombok.Getter;

import java.io.Serial;

/**
 * Exception producida cuando un Demo no es encontrado.
 */
@Getter
public class EmpleadoAutenticacionException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3302872999292045132L;

    /**
     * -- GETTER --
     * */
    private  String errorCode;

    /**
     * Constructor para la exception.
     *
     * @param mensaje El mensaje de error.
     */
    public EmpleadoAutenticacionException(String mensaje) {
        super(mensaje);
    }

    /**
     *
     * @param errorCode
     * @param mensaje
     */
    public EmpleadoAutenticacionException(String errorCode, String mensaje) {
        super(mensaje);
        this.errorCode = errorCode;
    }

}