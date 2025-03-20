package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ContratoDetalleError {

    NO_ENCONTRADO("CTDT_000", 404) ,
    INTEGRIDAD_VIOLADA( "CTDT_001", 460),
    REQUERIDO("CTDT_002", 400) ,
    DUPLICADO("CTDT_003", 409) ,
    ID_REQUERIDO("CTDT_004", 400) ,
    ID_INVALIDO("CTDT_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ContratoDetalleError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
