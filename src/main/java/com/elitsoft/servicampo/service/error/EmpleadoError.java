package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum EmpleadoError {

    NO_ENCONTRADO("EMPL_000", 404),
    INTEGRIDAD_VIOLADA("EMPL_001", 460),
    REQUERIDO("EMPL_002", 400),
    DUPLICADO("EMPL_003", 409),
    CONTRASENA_REQUERIDO("EMPL_004", 400),
    CORREO_DUPLICADO("EMPL_005", 409),
    CORREO_REQUERIDO("EMPL_006", 400),
    CORREO_NO_ENCONTRADO("EMPL_007", 404),
    ID_REQUERIDO("EMPL_008", 400),
    ID_INVALIDO("EMPL_009", 400),
    NO_AUTORIZADO("EMPL_010", 200),
    DESABILITADO("EMPL_011", 401);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    EmpleadoError(String codigoError, Integer httpCodigoRespuesta) {
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
