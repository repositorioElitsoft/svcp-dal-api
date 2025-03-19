package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.Cliente;
import com.elitsoft.servicampo.domain.entity.Contacto;
import com.elitsoft.servicampo.domain.entity.Estado;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;


@Data
public class ContratoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 5050150029418740340L;

    //filtros
    private Long id;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    private Long estado;
    private Long contacto;
    private Long cliente;
    //private List<ContratoDetalle> contratosDetalle;


}