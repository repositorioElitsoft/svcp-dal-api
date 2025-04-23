package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EstadoComponenteMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5615956068540610277L;

    private Long id;
    private String nombre;

}