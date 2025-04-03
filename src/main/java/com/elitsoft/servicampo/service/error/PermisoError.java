package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum PermisoError {

    NO_ENCONTRADO("PRMS_000", 404),
    INTEGRIDAD_VIOLADA("PRMS_001", 460),
    REQUERIDO("PRMS_002", 400),
    DUPLICADO("PRMS_003", 409),
    ID_REQUERIDO("PRMS_004", 400),
    ID_INVALIDO("PRMS_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    PermisoError(String codigoError, Integer httpCodigoRespuesta) {
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
