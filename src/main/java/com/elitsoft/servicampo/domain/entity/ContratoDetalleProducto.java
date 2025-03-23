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
public class ContratoDetalleProducto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4955131514274295217L;

    private ContratoDetalle contratoDetalle;
    private Long correlativo;
    private TipoProducto tipoProducto;
    private Integer valorReferencia;
    private Producto producto;
    //private ContratoDetalleTipoProducto contratoDetalleTipoProducto;

}