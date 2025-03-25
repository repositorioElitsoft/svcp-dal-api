package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 */
@Setter
@Getter
public class Contacto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1116259564508791437L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String imagenPerfil;
    private String correoElectronico;
    private Long telefonoFijo;
    private Long telefonoMovil;
    private DocumentoIdentificacion documentoIdentificacion;

}