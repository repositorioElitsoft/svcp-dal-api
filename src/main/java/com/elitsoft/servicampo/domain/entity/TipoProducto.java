package com.elitsoft.servicampo.domain.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Setter
@Getter
public class TipoProducto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4480266311091123550L;

    private Long id;
    private String descripcionTipoProducto;
    private List<TipoProductoTipoComponente> tipoProductoTipoComponentes;
}
