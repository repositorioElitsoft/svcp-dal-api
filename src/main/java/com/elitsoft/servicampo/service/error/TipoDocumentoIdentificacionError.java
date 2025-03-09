package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum TipoDocumentoIdentificacionError {

    NO_ENCONTRADO("TPDI_000", 404),
    INTEGRIDAD_VIOLADA( "TPDI_001", 460),
    REQUERIDO("TPDI_002", 400),
    DUPLICADO("TPDI_003", 409),
    ID_REQUERIDO("TPDI_004", 400),
    ID_INVALIDO("TPDI_005", 400) ;

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    TipoDocumentoIdentificacionError(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
