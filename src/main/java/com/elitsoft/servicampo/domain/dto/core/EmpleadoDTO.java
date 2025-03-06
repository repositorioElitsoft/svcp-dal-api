package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO implements Serializable  {

    @Serial
    private static final long serialVersionUID = -3635105071980322695L;

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
    private TipoEmpleadoDto tipoEmpleado;
    private RoleDTO role;
    private EstadoDTO estado;
    private DocumentoIdentificacionDTO documentoIdentificacion;

}