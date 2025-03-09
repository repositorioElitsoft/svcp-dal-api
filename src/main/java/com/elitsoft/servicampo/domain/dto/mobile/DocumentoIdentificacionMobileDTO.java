package com.elitsoft.servicampo.domain.dto.mobile;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DocumentoIdentificacionMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6871465345202932215L;

    private Long id;
    private String numero;
    private Character digitoVerificador;
    private TipoDocumentoIdentificacionMobileDTO tipoDocumentoIdentificacion;

}