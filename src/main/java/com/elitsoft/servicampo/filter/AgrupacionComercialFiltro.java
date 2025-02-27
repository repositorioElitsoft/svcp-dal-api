package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class AgrupacionComercialFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 7722534841083208085L;

    private Long id;
    private String nombreGrupoComercial;

}