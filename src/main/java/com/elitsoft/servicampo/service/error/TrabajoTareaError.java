package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TrabajoTareaError {

    NO_ENCONTRADO("TRTR_000", 404),
    INTEGRIDAD_VIOLADA("TRTR_001", 460),
    REQUERIDO("TRTR_002", 400),
    DUPLICADO("TRTR_003", 409),
    ID_REQUERIDO("TRTR_004", 400),
    ID_INVALIDO("TRTR_005", 400);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TrabajoTareaError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }
} 