package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class RoleMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1723809065443606122L;

    private Long id;
    private String nombreRol;
}