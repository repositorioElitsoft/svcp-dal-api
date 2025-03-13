package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.EstadoProducto;
import com.elitsoft.servicampo.domain.entity.TipoComponente;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
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
    private TipoProductoDTO tipoProducto;
    private TipoComponenteDTO tipoComponente;
    private EstadoProductoDTO estadoProducto;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;
    private String imagenPerfil;
}