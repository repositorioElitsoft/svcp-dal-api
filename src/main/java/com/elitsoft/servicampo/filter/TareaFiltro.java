package com.elitsoft.servicampo.filter;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TareaFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6027913735314770759L;

    private Long id;
    private String descripcionTarea;

}