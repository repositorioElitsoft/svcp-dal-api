package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ClasificacionClienteMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6450962172346295417L;

    private Long id;
    private String clasificacionClienteDesc;


}