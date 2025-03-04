package com.elitsoft.servicampo.domain.dto.core;

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
public class TipoProductoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5579300936305724693L;

    private Long id;
    private String descripcionTipoProducto;
}