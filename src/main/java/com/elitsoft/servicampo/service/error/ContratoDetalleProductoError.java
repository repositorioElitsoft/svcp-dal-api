package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ContratoDetalleProductoError {

    NO_ENCONTRADO("CDPR_000", 404) ,
    INTEGRIDAD_VIOLADA( "CDPR_001", 460),
    REQUERIDO("CDPR_002", 400) ,
    DUPLICADO("CDPR_003", 409) ,
    ID_REQUERIDO("CDPR_004", 400) ,
    ID_INVALIDO("CDPR_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ContratoDetalleProductoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
