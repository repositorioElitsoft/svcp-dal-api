package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ContactoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7860946562037303604L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String imagenPerfil;
    private String correoElectronico;
    private Long telefonoFijo;
    private Long telefonoMovil;
    private DocumentoIdentificacionMobileDTO documentoIdentificacion;
    private ClienteMobileDTO cliente;
}