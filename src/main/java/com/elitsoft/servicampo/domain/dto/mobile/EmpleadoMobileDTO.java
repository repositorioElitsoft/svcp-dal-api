package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.DocumentoIdentificacionDTO;
import com.elitsoft.servicampo.domain.dto.core.EstadoDTO;
import com.elitsoft.servicampo.domain.dto.core.RoleDTO;
import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private TipoEmpleadoDto tipoEmpleado;
    private RoleDTO role;
    private EstadoDTO estado;
    private DocumentoIdentificacionDTO documentoIdentificacion;
}