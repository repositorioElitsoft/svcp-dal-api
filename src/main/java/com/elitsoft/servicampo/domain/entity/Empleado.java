package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
public class Empleado implements Serializable {

    @Serial
    private static final long serialVersionUID = -844167942075002521L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String imagenPerfil;
    private String telefonoFijo;
    private String telefonoMovil;
    private LocalDate fechaNacimiento;
    private String email;
    private String contrasena;
    private String nombreUsuario;
    private TipoEmpleado tipoEmpleado;
    private Role role;
    private Estado estado;
    private DocumentoIdentificacion documentoIdentificacion;
    private List<DireccionEmpleado> direccionesEmpleados;
}

