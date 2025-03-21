package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ContratoDetalleTipoProductoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1593606249323493488L;

    private ContratoDetalleMobileDTO contratoDetalle;
    private TipoProductoMobileDTO tipoProducto;
    private Integer totalProducto;

}