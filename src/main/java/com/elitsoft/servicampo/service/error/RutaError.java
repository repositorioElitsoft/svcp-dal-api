package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum RutaError {

    NO_ENCONTRADO("RUTA_000", 404),
    INTEGRIDAD_VIOLADA("RUTA_001", 460),
    REQUERIDO("RUTA_002", 400),
    DUPLICADO("RUTA_003", 409),
    ID_REQUERIDO("RUTA_004", 400),
    ID_INVALIDO("RUTA_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    RutaError(String codigoError, Integer httpCodigoRespuesta) {
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
