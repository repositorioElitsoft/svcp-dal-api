package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DireccionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2338427016614652141L;

    private Long id;
    private ClienteDTO cliente;
    private String descripcion;
    private String calle;
    private String numeracion;
    private String referencia;
    private ComunaDTO comuna;
    private SectorDTO sector;
    private ContactoDTO contacto;
    private TipoDireccionDTO tipoDireccion;
    private String imagenPerfil;
    private Double latitud;
    private Double longitud;
    private EstadoDTO estado;
    private String flagEvidencia;
}