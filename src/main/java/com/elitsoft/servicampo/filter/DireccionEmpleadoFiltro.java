package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class DireccionEmpleadoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6417435267151767572L;

    private Long id;
    private Long estadoId;
    private Long comunaId;
    private String calle;
    private String numeracion;
    private Double latitud;
    private Double longitud;
    private String descripcion;
    private String referencia;
    private Long empleadoId;

}