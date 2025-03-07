package com.elitsoft.servicampo.utils;

import lombok.Getter;

@Getter
public enum ErroresNegocio {

    //Modulo Empleados
    EMPLEADO_DUPLICADO("EMP_001", 409) , //df
    EMPLEADO_CONTRASENA_REQUERIDO("EMPL_002", 400),
    EMPLEADO_CORREO_DUPLICADO("EMPL_003", 409),
    EMPLEADO_CORREO_REQUERIDO("EMPL_004", 400),
    IDENTIFICACION_DUPLICADO("IDENT_001", 409),
    IDENTIFICACION_NUMERO_REQUERIDO("IDENT_002", 400),
    IDENTIFICACION_DIGITO_VERIFICADOR_REQUERIDO("IDENT_003", 400),
    TIPO_DOCUMENTO_IDENTIFICACION_ID_DUPLICADO("TIP_IDENT_001", 409),
    TIPO_DOCUMENTO_IDENTIFICACION_ID_REQUERIDO("TIP_IDENT_002", 400),
    TIPO_DOCUMENTO_IDENTIFICACION_NO_ENCONTRADO("TIP_IDENT_003", 404),

    INTEGRIDAD_VIOLADA( "INTG_001", 400);

    private final String codigoError;
    private final Integer httpCodigoRespuesta;

    ErroresNegocio(String codigoError, Integer httpCodigoRespuesta){
        this.codigoError = codigoError;
        this.httpCodigoRespuesta = httpCodigoRespuesta;
    }

}
