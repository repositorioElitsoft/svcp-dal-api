package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class RutaMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8457787895601518457L;

    private Long id;
    private String descripcion;

}