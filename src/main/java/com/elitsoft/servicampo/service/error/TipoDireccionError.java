package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoDireccionError {

    NO_ENCONTRADO("TIDI_000", 404),
    INTEGRIDAD_VIOLADA( "TIDI_001", 460),
    REQUERIDO("TIDI_002", 400) ,
    DUPLICADO("TIDI_003", 409) ,
    ID_REQUERIDO("TIDI_004", 400) ,
    ID_INVALIDO("TIDI_005", 400) ;

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoDireccionError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
