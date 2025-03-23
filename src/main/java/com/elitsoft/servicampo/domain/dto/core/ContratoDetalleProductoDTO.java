package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.ContratoDetalle;
import com.elitsoft.servicampo.domain.entity.Producto;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ContratoDetalleProductoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 747657581574908811L;

    private ContratoDetalleDTO contratoDetalle;
    private Long correlativo;
    private TipoProductoDTO tipoProducto;
    private ProductoDTO producto;
    private Integer valorReferencia;
}