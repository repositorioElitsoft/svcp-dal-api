package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class RegionMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4612032313380177934L;

    private Long id;
    private String descripcionRegion;
    private PaisMobileDTO pais;

}