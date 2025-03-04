package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 *
 */
public class Estado implements Serializable {

    @Serial
    private static final long serialVersionUID = -6712612439417290731L;

    private Long id;
    private String descripcion;
}