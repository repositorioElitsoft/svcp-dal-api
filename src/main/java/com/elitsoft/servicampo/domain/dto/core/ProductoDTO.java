package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ProductoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3337148375489379884L;

    private Long id;
    private String descripcion;
    private CarroDTO carro;
    private TipoProductoDTO tipoProducto;
    private TipoComponenteDTO tipoComponente;
    private EstadoProductoDTO estadoProducto;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;
}