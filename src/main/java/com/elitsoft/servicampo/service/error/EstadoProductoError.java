package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum EstadoProductoError {

    NO_ENCONTRADO("ESPR_000", 404) ,
    INTEGRIDAD_VIOLADA( "ESPR_001", 460),
    REQUERIDO("ESPR_002", 400) ,
    DUPLICADO("ESPR_003", 409) ,
    ID_REQUERIDO("ESPR_004", 400) ,
    ID_INVALIDO("ESPR_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    EstadoProductoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
