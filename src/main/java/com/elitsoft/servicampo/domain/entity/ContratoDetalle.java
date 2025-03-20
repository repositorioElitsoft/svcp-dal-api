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
public class ContratoDetalle implements Serializable {

    @Serial
    private static final long serialVersionUID = -3835864177772984834L;


    private Long id;
    private Contrato contrato;
    private Direccion direccion;
    private Servicio servicio;
    //private List<OrdenTrabajo> ordenesTrabajo;
    //private List<ContratoDetalleTipoProducto> contratosDetalleTipoProducto;
    //private List<ContratoDetalleProducto> contratosDetalleProducto;


}