package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabajoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5994822367625238261L;

    private Long id;
    private String descripcionTrabajo;


}