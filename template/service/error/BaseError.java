package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum #Base#Error {

    NO_ENCONTRADO("XXXX_000", 404) ,
    INTEGRIDAD_VIOLADA( "XXXX_001", 460),
    REQUERIDO("XXXX_002", 400) ,
    DUPLICADO("XXXX_003", 409) ,
    ID_REQUERIDO("XXXX_004", 400) ,
    ID_INVALIDO("XXXX_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    #Base#Error(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
