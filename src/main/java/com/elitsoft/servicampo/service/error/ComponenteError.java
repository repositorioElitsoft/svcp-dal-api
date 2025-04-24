package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ComponenteError {

    NO_ENCONTRADO("COMP_000", 404) ,
    INTEGRIDAD_VIOLADA( "COMP_001", 460),
    REQUERIDO("COMP_002", 400) ,
    DUPLICADO("COMP_003", 409) ,
    ID_REQUERIDO("COMP_004", 400) ,
    ID_INVALIDO("COMP_005", 400) ,
    CODiGO_REQUERIDO("COMP_006", 400) ;


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ComponenteError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
