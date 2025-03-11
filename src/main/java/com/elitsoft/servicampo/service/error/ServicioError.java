package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ServicioError {

    NO_ENCONTRADO("SVCS_000", 404) ,
    INTEGRIDAD_VIOLADA( "SVCS_001", 460),
    REQUERIDO("SVCS_002", 400) ,
    DUPLICADO("SVCS_003", 409) ,
    ID_REQUERIDO("SVCS_004", 400) ,
    ID_INVALIDO("SVCS_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ServicioError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
