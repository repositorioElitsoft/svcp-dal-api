package com.elitsoft.servicampo.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


/**
 *
 */
@Setter
@Getter
public class Estado implements Serializable {

    @Serial
    private static final long serialVersionUID = -6712612439417290731L;

    private Long id;
    private String descripcion;
}