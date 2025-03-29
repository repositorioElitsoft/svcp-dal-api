package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum SegmentacionClienteError {

    NO_ENCONTRADO("SGCL_000", 404),
    INTEGRIDAD_VIOLADA("SGCL_001", 460),
    REQUERIDO("SGCL_002", 400),
    DUPLICADO("SGCL_003", 409),
    ID_REQUERIDO("SGCL_004", 400),
    ID_INVALIDO("SGCL_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    SegmentacionClienteError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

} 