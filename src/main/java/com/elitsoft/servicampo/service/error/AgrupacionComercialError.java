package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum AgrupacionComercialError {

    NO_ENCONTRADO("GRCM_000", 404),
    INTEGRIDAD_VIOLADA("GRCM_001", 460),
    REQUERIDO("GRCM_002", 400),
    DUPLICADO("GRCM_003", 409),
    ID_REQUERIDO("GRCM_004", 400),
    ID_INVALIDO("GRCM_005", 400);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    AgrupacionComercialError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }
} 