package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum CarroError {

    NO_ENCONTRADO("CARR_000", 404) ,
    INTEGRIDAD_VIOLADA( "CARR_001", 460),
    REQUERIDO("CARR_002", 400) ,
    DUPLICADO("CARR_003", 409) ,
    ID_REQUERIDO("CARR_004", 400) ,
    ID_INVALIDO("CARR_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    CarroError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
