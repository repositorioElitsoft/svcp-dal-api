package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ContratoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8372585733704221727L;

    private Long id;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    private EstadoDTO estado;
    private ContactoDTO contacto;
    private ClienteDTO cliente;
    //private List<ContratoDetalle> contratosDetalle;
}