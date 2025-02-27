package com.elitsoft.servicampo.domain.entity;

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
/**
 *
 */
public class TipoProducto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4480266311091123550L;

    private Long id;
    private String descripcionTipoProducto;
}
