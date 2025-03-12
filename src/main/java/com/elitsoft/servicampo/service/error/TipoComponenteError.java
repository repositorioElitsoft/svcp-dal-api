package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoComponenteError {

    NO_ENCONTRADO("TPCM_000", 404) ,
    INTEGRIDAD_VIOLADA( "TPCM_001", 460),
    REQUERIDO("TPCM_002", 400) ,
    DUPLICADO("TPCM_003", 409) ,
    ID_REQUERIDO("TPCM_004", 400) ,
    ID_INVALIDO("TPCM_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoComponenteError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
