package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum EstadoError {

    NO_ENCONTRADO("ESTD_000", 404),
    INTEGRIDAD_VIOLADA("ESTD_001", 460),
    REQUERIDO("ESTD_002", 400),
    DUPLICADO("ESTD_003", 409),
    ID_REQUERIDO("ESTD_004", 400),
    ID_INVALIDO("ESTD_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    EstadoError(String codigoError, Integer httpCodigoRespuesta) {
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
