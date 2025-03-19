package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ContratoError {

    NO_ENCONTRADO("CTTS_000", 404) ,
    INTEGRIDAD_VIOLADA( "CTTS_001", 460),
    REQUERIDO("CTTS_002", 400) ,
    DUPLICADO("CTTS_003", 409) ,
    ID_REQUERIDO("CTTS_004", 400) ,
    ID_INVALIDO("CTTS_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ContratoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
