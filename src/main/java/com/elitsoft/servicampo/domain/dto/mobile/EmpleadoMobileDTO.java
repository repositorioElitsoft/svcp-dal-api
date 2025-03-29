package com.elitsoft.servicampo.domain.dto.mobile;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
public class EmpleadoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2466401363555100492L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String imagenPerfil;
    private String telefonoFijo;
    private String telefonoMovil;
    private LocalDate fechaNacimiento;
    private String email;
    private String nombreUsuario;
    private TipoEmpleadoMobileDTO tipoEmpleado;
    private RoleMobileDTO role;
    private EstadoMobileDTO estado;
    private DocumentoIdentificacionMobileDTO documentoIdentificacion;
}