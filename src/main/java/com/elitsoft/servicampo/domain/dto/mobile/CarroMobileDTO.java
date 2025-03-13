package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CarroMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5397101279620639595L;

    private Long id;
    private String numero;
    private String modelo;
    private String caracteristica;
    private String proveedor;
}