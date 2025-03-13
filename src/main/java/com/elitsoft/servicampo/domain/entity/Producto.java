package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 */
@Setter
@Getter
public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = -1270261488266314892L;

    private Long id;
    private String descripcion;
    private TipoProducto tipoProducto;
    private TipoComponente tipoComponente;
    private EstadoProducto estadoProducto;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;
    private String imagenPerfil;



}