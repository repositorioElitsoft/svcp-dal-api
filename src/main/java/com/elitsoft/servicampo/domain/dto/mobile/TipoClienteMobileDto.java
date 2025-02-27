package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class TipoClienteMobileDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2710459170040583178L;

    private Long id;
    private String nombre;

}