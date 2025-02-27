package com.elitsoft.servicampo.filter;

import com.elitsoft.servicampo.domain.entity.Zona;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SectorFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1971930744361136229L;

    private Long id;
    private String descripcionSector;
    private Long zonaId;

}