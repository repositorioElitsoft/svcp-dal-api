package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum RoleError {

    NO_ENCONTRADO("RLES_000", 404) ,
    INTEGRIDAD_VIOLADA( "RLES_001", 460),
    REQUERIDO("RLES_002", 400) ,
    DUPLICADO("RLES_003", 409) ,
    ID_REQUERIDO("RLES_004", 400) ,
    ID_INVALIDO("RLES_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    RoleError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
