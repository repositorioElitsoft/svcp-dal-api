package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class SegmentacionClienteMobileDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7949530273436531811L;

    private Long id;
    private String descripcion;

}