package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ComunaFiltro implements Serializable {

    private Long id;
    private String descripcionComuna;
    private Long provinciaId;

}