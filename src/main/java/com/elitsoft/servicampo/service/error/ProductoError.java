package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ProductoError {

    NO_ENCONTRADO("PROD_000", 404) ,
    INTEGRIDAD_VIOLADA( "PROD_001", 460),
    REQUERIDO("PROD_002", 400) ,
    DUPLICADO("PROD_003", 409) ,
    ID_REQUERIDO("PROD_004", 400) ,
    ID_INVALIDO("PROD_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ProductoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
