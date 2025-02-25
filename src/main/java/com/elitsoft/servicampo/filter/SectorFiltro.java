package com.elitsoft.servicampo.filter;

import com.elitsoft.servicampo.domain.entity.Zona;
import lombok.Data;

@Data
public class SectorFiltro {

    private Long id;
    private String descripcionSector;
    private Long zonaId;

}