package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AgrupacionComercialMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1835706989681421884L;

    private Long id;
    private String nombreGrupoComercial;

}