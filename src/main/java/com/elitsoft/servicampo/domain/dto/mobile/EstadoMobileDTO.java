package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class EstadoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4085112034288124965L;

    private Long id;
    private String descripcion;

}