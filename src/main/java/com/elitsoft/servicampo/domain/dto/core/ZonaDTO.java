package com.elitsoft.servicampo.domain.dto.core;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class ZonaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5236474049459159372L;

    private Long id;
    private String descripcionZona;
}