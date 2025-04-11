package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1684527147327392722L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String imagenPerfil;
    private String email;
    private String campo1;
    private String campo2;
    private String telefonoFijo;
    private String telefonoMovil;
    private TipoClienteMobileDTO tipoCliente;
    private ClasificacionCliente clasificacionCliente;
    private EstadoMobileDTO estado;
    //private List<DireccionMobileDTO> direcciones;
    private AgrupacionComercialMobileDTO agrupacionComercial;
    private SegmentacionClienteMobileDTO segmentacionCliente;
    private DocumentoIdentificacionMobileDTO documentoIdentificacion;
    //private List<ContactoMobileDTO> contactos;

}