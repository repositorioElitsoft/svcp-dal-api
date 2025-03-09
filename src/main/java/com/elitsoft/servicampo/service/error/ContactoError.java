package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ContactoError {

    NO_ENCONTRADO("CNTC_000", 404) ,
    INTEGRIDAD_VIOLADA( "CNTC_001", 460),
    REQUERIDO("CNTC_002", 400) ,
    DUPLICADO("CNTC_003", 409) ,
    ID_REQUERIDO("CNTC_004", 400) ,
    ID_INVALIDO("CNTC_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ContactoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
