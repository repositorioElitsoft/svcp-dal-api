package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum GeneralError {

    ERROR_INTERNO("ERRI_000",500);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    GeneralError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
