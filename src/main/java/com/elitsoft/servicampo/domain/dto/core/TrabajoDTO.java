package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class TrabajoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5994822367625238261L;

    private Long id;
    private String descripcionTrabajo;


}