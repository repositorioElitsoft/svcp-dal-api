package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum EstadoComponenteError {

    NO_ENCONTRADO("ESCM_000", 404),
    INTEGRIDAD_VIOLADA("ESCM_001", 460),
    REQUERIDO("ESCM_002", 400),
    DUPLICADO("ESCM_003", 409),
    ID_REQUERIDO("ESCM_004", 400),
    ID_INVALIDO("ESCM_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    EstadoComponenteError(String codigoError, Integer httpCodigoRespuesta) {
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
