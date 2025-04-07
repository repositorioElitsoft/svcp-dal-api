package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum ClienteError {

    NO_ENCONTRADO("CLNT_000", 404) ,
    INTEGRIDAD_VIOLADA( "CLNT_001", 460),
    REQUERIDO("CLNT_002", 400) ,
    DUPLICADO("CLNT_003", 409) ,
    CONTRASENA_REQUERIDO("CLNT_004", 400),
    CORREO_DUPLICADO("CLNT_005", 409),
    CORREO_REQUERIDO("CLNT_006", 400),
    CORREO_NO_ENCONTRADO("CLNT_007", 404),
    ID_REQUERIDO("CLNT_008", 400) ,
    ID_INVALIDO("CLNT_009", 400) ,
    IMAGEN_SUBIR("CLNT_010", 500),
    IMAGEN_BAJAR("CLNT_011", 500),
    IMAGEN_BAJAR_NO_ENCONTRADO("CLNT_012", 404);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ClienteError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
