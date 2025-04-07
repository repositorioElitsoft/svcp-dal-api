package com.elitsoft.servicampo.exceptions;

import lombok.Getter;

import java.io.Serial;

/**
 *
 */
@Getter
public class ArchivoEntradaSalidaException extends Exception{

    @Serial
    private static final long serialVersionUID = -5632648488516868413L;

    /**
     * -- GETTER --
     */
    private  String errorCode;

    /**
     * @param mensaje
     */
    public ArchivoEntradaSalidaException(String mensaje) {
        super(mensaje);
    }


    public ArchivoEntradaSalidaException(String errorCode, String mensaje) {
        super(mensaje);
        this.errorCode = errorCode;
    }

    /**
     * @param mensaje
     * @param causa
     */
    public ArchivoEntradaSalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ArchivoEntradaSalidaException(String errorCode, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.errorCode = errorCode;
    }

}
