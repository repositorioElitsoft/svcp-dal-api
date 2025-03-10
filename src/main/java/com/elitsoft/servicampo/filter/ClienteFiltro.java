package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ClienteFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -5080970777765123056L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long tipoClienteId;
    private Long clasificacionClienteId;
    private Long estadoId;
    private Long agrupacionComercialId;
    private Long segmentacionClienteId;

}