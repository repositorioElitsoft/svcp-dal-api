package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DireccionEmpleadoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4621921481295668388L;

    private Long id;
    private EstadoMobileDTO estado;
    private ComunaMobileDTO comuna;
    private String calle;
    private String numeracion;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String referencia;
    private EmpleadoMobileDTO empleado;

}