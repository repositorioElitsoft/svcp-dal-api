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
public class ContratoDetalleTipoProducto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4748366870102706200L;

    private ContratoDetalle contratoDetalle;
    private TipoProducto tipoProducto;
    private Integer totalProducto;
    //private List<ContratoDetalleProducto> contratosDetalleProducto;



}