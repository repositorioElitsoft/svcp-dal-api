package com.elitsoft.servicampo.domain.dto.core;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class TipoProductoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5579300936305724693L;

    private Long id;
    private String descripcionTipoProducto;
    private List<TipoProductoTipoComponenteDTO> tipoProductoTipoComponentes;
}