package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7054852836615483383L;

    private Long id;
    private String nombreRol;
}