package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoServicioError {

    NO_ENCONTRADO("TPSEV_000", 404),
    INTEGRIDAD_VIOLADA("TPSEV_001", 460),
    REQUERIDO("TPSEV_002", 400),
    DUPLICADO("TPSEV_003", 409),
    ID_REQUERIDO("TPSEV_004", 400),
    ID_INVALIDO("TPSEV_005", 400);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoServicioError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }
} 