package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrabajoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3154325653146423023L;

    private Long id;
    private String descripcionTrabajo;

}