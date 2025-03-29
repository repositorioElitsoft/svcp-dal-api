package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ZonaError {

    NO_ENCONTRADO("ZNAS_000", 404),
    INTEGRIDAD_VIOLADA("ZNAS_001", 460),
    REQUERIDO("ZNAS_002", 400),
    DUPLICADO("ZNAS_003", 409),
    ID_REQUERIDO("ZNAS_004", 400),
    ID_INVALIDO("ZNAS_005", 400);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ZonaError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }
} 