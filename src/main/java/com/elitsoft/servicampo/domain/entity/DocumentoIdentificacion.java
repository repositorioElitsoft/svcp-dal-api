package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */
@Setter
@Getter
public class DocumentoIdentificacion implements Serializable {

    @Serial
    private static final long serialVersionUID = -442030994222881206L;

    private Long id;
    private String numero;
    private Character digitoVerificador;
    private TipoDocumentoIdentificacion tipoDocumentoIdentificacion;



}