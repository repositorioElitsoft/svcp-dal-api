package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class RegionFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6113720968751115188L;

    private Long id;
    private String descripcionRegion;
    private Long paisId;
}