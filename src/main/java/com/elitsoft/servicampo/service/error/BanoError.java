package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum BanoError {

    NO_ENCONTRADO("BANS_000", 404) ,
    INTEGRIDAD_VIOLADA( "BANS_001", 460),
    REQUERIDO("BANS_002", 400) ,
    DUPLICADO("BANS_003", 409) ,
    ID_REQUERIDO("BANS_004", 400) ,
    ID_INVALIDO("BANS_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    BanoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
