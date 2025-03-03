package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.Region;
import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PaisDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -2709146679942359192L;

    private Long id;
    private String descripcionPais;

}