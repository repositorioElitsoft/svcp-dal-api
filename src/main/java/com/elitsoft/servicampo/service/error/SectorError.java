package com.elitsoft.servicampo.service.error;

import lombok.Getter;

@Getter
public enum SectorError {

    NO_ENCONTRADO("SCTR_000", 404),
    INTEGRIDAD_VIOLADA("SCTR_001", 460),
    REQUERIDO("SCTR_002", 400),
    DUPLICADO("SCTR_003", 409),
    ID_REQUERIDO("SCTR_004", 400),
    ID_INVALIDO("SCTR_005", 400);


    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    SectorError(String codigoError, Integer httpCodigoRespuesta) {
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
