package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoComponenteDTO;
import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoProductoTipoComponenteMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8321769964826409405L;

    private TipoComponenteMobileDTO tipoComponente;
    private TipoProductoMobileDTO tipoProducto;
    private int cantidad;

}