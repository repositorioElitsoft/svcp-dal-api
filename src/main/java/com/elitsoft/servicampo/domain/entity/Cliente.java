package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@Setter
@Getter
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 21644640749443157L;


    private Long id;
    private String nombre;
    private String apellidoPaterno; //= "";
    private String apellidoMaterno; //= "";
    private Integer rut;
    private Character rutDv;
    private LocalDate fechaNacimiento; //= LocalDate.of(1900, 1, 1);
    private String imagenPerfil;
    private String email;
    private String campo1;
    private String campo2;
    private String telefonoFijo;
    private String telefonoMovil;
    private TipoCliente tipoCliente;
    private ClasificacionCliente clasificacionCliente;
    private Estado estado;
    private List<Direccion> direcciones;
    private AgrupacionComercial agrupacionComercial;
    private SegmentacionCliente segmentacionCliente;
    private DocumentoIdentificacion documentoIdentificacion;
    //private List<Contacto> contactos;



}