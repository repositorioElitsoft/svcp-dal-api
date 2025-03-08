package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.Zona;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class SectorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5346232492548509729L;
    private Long id;
    private String descripcionSector;
    private Zona zona;
}