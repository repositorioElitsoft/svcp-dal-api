package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.PaisDto;
import com.elitsoft.servicampo.domain.entity.Pais;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class RegionMobileDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4612032313380177934L;

    private Long id;
    private String descripcionRegion;
    private PaisDto pais;

}