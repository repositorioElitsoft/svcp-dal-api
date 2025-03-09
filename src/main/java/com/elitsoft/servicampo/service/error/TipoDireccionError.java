package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoDireccionError {

    NO_ENCONTRADO("TPDR_000", 404),
    INTEGRIDAD_VIOLADA( "TPDR_001", 460),
    REQUERIDO("TPDR_002", 400) ,
    DUPLICADO("TPDR_003", 409) ,
    ID_REQUERIDO("TPDR_004", 400) ,
    ID_INVALIDO("TPDR_005", 400) ;

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoDireccionError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
