package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class SegmentacionClienteFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6134103468506875039L;

    private Long id;
    private String descripcion;

}