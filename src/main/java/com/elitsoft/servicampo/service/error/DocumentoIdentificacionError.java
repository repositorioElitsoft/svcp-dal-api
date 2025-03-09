package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum DocumentoIdentificacionError {

    NO_ENCONTRADO("IDENT_000", 404),
    INTEGRIDAD_VIOLADA( "IDENT_001", 460),
    REQUERIDO("IDENT_002", 400),
    DUPLICADO("IDENT_003", 409),
    NUMERO_REQUERIDO("IDENT_004", 400),
    DIGITO_VERIFICADOR_REQUERIDO("IDENT_005", 400);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    DocumentoIdentificacionError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
