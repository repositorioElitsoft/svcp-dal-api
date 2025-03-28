package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoClienteError {

    NO_ENCONTRADO("TPCL_000", 404),
    INTEGRIDAD_VIOLADA( "TPCL_001", 460),
    REQUERIDO("TPCL_002", 400) ,
    DUPLICADO("TPCL_003", 409) ,
    ID_REQUERIDO("TPCL_004", 400) ,
    ID_INVALIDO("TPCL_005", 400) ;

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoClienteError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
