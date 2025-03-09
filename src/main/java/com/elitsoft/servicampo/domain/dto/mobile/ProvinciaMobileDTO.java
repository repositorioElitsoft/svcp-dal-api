package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ProvinciaMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -977883584171546530L;

    private Long id;
    private String descripcionCiudad;
    private RegionMobileDTO region;

}