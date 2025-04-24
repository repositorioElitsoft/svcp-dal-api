package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

/**
 * Entidad que representa un Componente
 */
@Setter
@Getter
public class Componente implements Serializable {

    @Serial
    private static final long serialVersionUID = -2566307606457809454L;

    private Long id;
    private TipoComponente tipoComponente;
    private EstadoComponente estadoComponente;
    private Date fechaCambio;
    private String codigo;
    private String proveedor;
    private String observacion;
    private String modelo;
}