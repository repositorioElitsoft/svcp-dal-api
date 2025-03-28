package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoEmpleadoError {

    NO_ENCONTRADO("TPEM_000", 404),
    INTEGRIDAD_VIOLADA( "TPEM_001", 460),
    REQUERIDO("TPEM_002", 400) ,
    DUPLICADO("TPEM_003", 409) ,
    ID_REQUERIDO("TPEM_004", 400) ,
    ID_INVALIDO("TPEM_005", 400) ;

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoEmpleadoError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
