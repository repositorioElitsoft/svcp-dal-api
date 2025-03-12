package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.TipoComponente;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoProductoTipoComponenteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8380782444160167674L;

    private TipoComponenteDTO tipoComponente;
    private TipoProductoDTO tipoProducto;
    private int cantidad;
}