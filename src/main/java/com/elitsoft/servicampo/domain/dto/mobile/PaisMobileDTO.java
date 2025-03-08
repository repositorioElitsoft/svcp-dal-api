package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class PaisMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5492830918562776751L;

    private Long id;
    private String descripcionPais;

}