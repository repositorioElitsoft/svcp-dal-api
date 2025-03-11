package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum DireccionError {

    NO_ENCONTRADO("DRCC_000", 404),
    INTEGRIDAD_VIOLADA( "DRCC_001", 460),
    REQUERIDO("DRCC_002", 400) ,
    DUPLICADO("DRCC_003", 409) ,
    ID_REQUERIDO("DRCC_004", 400) ,
    ID_INVALIDO("DRCC_005", 400) ;

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    DireccionError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
