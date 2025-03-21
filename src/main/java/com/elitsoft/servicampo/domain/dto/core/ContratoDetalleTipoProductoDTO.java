package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ContratoDetalleTipoProductoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9009173831824819849L;

    private ContratoDetalleDTO contratoDetalle;
    private TipoProductoDTO tipoProducto;
    private Integer totalProducto;

}