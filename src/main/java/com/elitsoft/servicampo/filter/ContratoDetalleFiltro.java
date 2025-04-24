package com.elitsoft.servicampo.filter;



import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ContratoDetalleFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -7086033854144491606L;

    //filtros
    private Long id;
    private Long contrato;
    private Long cliente;
    private Long servicio;

    //ordenamiento
    private Long direccion;

}