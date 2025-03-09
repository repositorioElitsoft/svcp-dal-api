package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum DocumentoIdentificacionError {

    NO_ENCONTRADO("DOID_000", 404),
    INTEGRIDAD_VIOLADA( "DOID_001", 460),
    REQUERIDO("DOID_002", 400),
    DUPLICADO("DOID_003", 409),
    NUMERO_REQUERIDO("DOID_004", 400),
    DIGITO_VERIFICADOR_REQUERIDO("DOID_005", 400);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    DocumentoIdentificacionError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
