package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ClasificacionClienteError {

    NO_ENCONTRADO("CLCL_000", 404),
    INTEGRIDAD_VIOLADA("CLCL_001", 460),
    REQUERIDO("CLCL_002", 400),
    DUPLICADO("CLCL_003", 409),
    ID_REQUERIDO("CLCL_004", 400),
    ID_INVALIDO("CLCL_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ClasificacionClienteError(String codigoError, Integer httpCodigoRespuesta) {
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
