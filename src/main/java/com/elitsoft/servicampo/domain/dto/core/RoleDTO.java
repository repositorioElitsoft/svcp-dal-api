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
public class RoleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7054852836615483383L;

    private Long id;
    private String nombreRol;
}