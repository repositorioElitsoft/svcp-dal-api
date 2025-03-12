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
public class TipoProductoTipoComponente implements Serializable {

    @Serial
    private static final long serialVersionUID = 8494053470049816822L;

    private TipoComponente tipoComponente;
    private TipoProducto tipoProducto;
    private int cantidad;
}