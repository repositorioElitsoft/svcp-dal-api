package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4391792097096255777L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer rut;
    private Character rutDv;
    private LocalDate fechaNacimiento;
    private String imagenPerfil;
    private String email;
    private String campo1;
    private String campo2;
    private String telefonoFijo;
    private String telefonoMovil;
    private TipoClienteDTO tipoCliente;
    private ClasificacionClienteDTO clasificacionCliente;
    private EstadoDTO estado;
    //private List<Direccion> direcciones;
    private AgrupacionComercialDTO agrupacionComercial;
    private SegmentacionClienteDTO segmentacionCliente;

}