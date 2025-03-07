package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.TipoDocumentoIdentificacion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DocumentoIdentificacionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6006337473602333536L;

    private Long id;
    private String numero;
    private Character digitoVerificador;
    private TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacion;
}