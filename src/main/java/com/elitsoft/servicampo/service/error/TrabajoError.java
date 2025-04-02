package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TrabajoError {

    NO_ENCONTRADO("TRBJ_000", 404),
    INTEGRIDAD_VIOLADA("TRBJ_001", 460),
    REQUERIDO("TRBJ_002", 400),
    DUPLICADO("TRBJ_003", 409),
    ID_REQUERIDO("TRBJ_004", 400),
    ID_INVALIDO("TRBJ_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TrabajoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

} 