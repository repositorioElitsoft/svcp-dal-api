package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.ProvinciaDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ComunaMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1955096054244825765L;

    private Long id;
    private String descripcionComuna;
    private ProvinciaDTO provincia;

}