package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoProductoError {

    NO_ENCONTRADO("TPPR_000", 404) ,
    INTEGRIDAD_VIOLADA( "TPPR_001", 460),
    REQUERIDO("TPPR_002", 400) ,
    DUPLICADO("TPPR_003", 409) ,
    ID_REQUERIDO("TPPR_004", 400) ,
    ID_INVALIDO("TPPR_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoProductoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
