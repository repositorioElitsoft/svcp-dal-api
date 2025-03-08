package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.ZonaDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class SectorMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4933043323172469303L;

    private Long id;
    private String descripcionSector;
    private ZonaDTO zona;
}