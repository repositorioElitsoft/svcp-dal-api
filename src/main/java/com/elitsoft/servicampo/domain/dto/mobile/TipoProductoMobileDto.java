package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoProductoMobileDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6481626244538500364L;

    private Long id;
    private String descripcionTipoProducto;
}