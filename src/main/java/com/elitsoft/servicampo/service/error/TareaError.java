package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TareaError {

    NO_ENCONTRADO("TRAS_000", 404),
    INTEGRIDAD_VIOLADA("TRAS_001", 460),
    REQUERIDO("TRAS_002", 400),
    DUPLICADO("TRAS_003", 409),
    ID_REQUERIDO("TRAS_004", 400),
    ID_INVALIDO("TRAS_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TareaError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

} 