package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class TipoProductoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6481626244538500364L;

    private Long id;
    private String descripcionTipoProducto;
}