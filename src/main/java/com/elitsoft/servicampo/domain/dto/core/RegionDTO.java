package com.elitsoft.servicampo.domain.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class RegionDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = 5513789348408986763L;

    private Long id;
    private String descripcionRegion;
    private PaisDTO pais;
}