package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Setter
@Getter
public class Direccion implements Serializable {

    @Serial
    private static final long serialVersionUID = -7401032446808793944L;

    private Long id;
    private Cliente cliente;
    private String descripcion;
    private String calle;
    private String numeracion;
    private String referencia;
    private Comuna comuna;
    private Sector sector;
    private List<Contacto> contactos;
    private TipoDireccion tipoDireccion;
    private String imagenPerfil;
    private Double latitud;
    private Double longitud;
    private Estado estado;
    private String flagEvidencia;
    private List<ContactoDireccion> contactoDireccion;
    private Ruta ruta;

}