package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

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
}
