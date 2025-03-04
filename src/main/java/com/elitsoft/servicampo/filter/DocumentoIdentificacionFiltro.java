package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.TipoDocumentoIdentificacion;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class DocumentoIdentificacionFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 4106135283199878255L;

    private Long id;
    private String numero;
    private Character digitoVerificador;
    private Long tipoDocumentoIdentificacionId;

}