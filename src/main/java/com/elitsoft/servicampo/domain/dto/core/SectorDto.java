package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.Zona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorDto {

    private Long id;
    private String descripcionSector;
    private Zona zona;
}