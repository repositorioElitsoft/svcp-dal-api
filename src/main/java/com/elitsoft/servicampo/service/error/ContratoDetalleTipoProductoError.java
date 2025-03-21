package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ContratoDetalleTipoProductoError {

    NO_ENCONTRADO("CDTP_000", 404) ,
    INTEGRIDAD_VIOLADA( "CDTP_001", 460),
    REQUERIDO("CDTP_002", 400) ,
    DUPLICADO("CDTP_003", 409) ,
    ID_REQUERIDO("CDTP_004", 400) ,
    ID_INVALIDO("CDTP_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ContratoDetalleTipoProductoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
