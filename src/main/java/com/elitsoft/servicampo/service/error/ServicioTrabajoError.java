package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ServicioTrabajoError {

    NO_ENCONTRADO("SVTR_000", 404) ,
    INTEGRIDAD_VIOLADA( "SVTR_001", 460),
    REQUERIDO("SVTR_002", 400) ,
    DUPLICADO("SVTR_003", 409) ,
    ID_REQUERIDO("SVTR_004", 400) ,
    ID_INVALIDO("SVTR_005", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ServicioTrabajoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
