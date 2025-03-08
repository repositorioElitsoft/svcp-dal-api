package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class PaisDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2709146679942359192L;

    private Long id;
    private String descripcionPais;

}