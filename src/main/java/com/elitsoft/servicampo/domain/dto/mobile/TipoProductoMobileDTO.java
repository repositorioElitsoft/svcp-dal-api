package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoTipoComponenteDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class TipoProductoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6481626244538500364L;

    private Long id;
    private String descripcionTipoProducto;
    private List<TipoProductoTipoComponenteMobileDTO> tipoProductoTipoComponentes;
}