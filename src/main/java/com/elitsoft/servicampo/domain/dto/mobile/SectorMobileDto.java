package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.entity.Zona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorMobileDto {

    private Long id;
    private String descripcionSector;
    private Zona zona;
}