package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoProductoTipoComponenteError {

    NO_ENCONTRADO("TPTC_000", 404) ,
    INTEGRIDAD_VIOLADA( "TPTC_001", 460),
    REQUERIDO("TPTC_002", 400) ,
    DUPLICADO("TPTC_003", 409) ,
    ID_REQUERIDO("TPTC_004", 400) ,
    ID_INVALIDO("TPTC_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoProductoTipoComponenteError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
