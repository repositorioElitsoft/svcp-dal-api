package com.elitsoft.servicampo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */
@Setter
@Getter
public class SegmentacionCliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1902714294880576105L;

    private Long id;
    private String descripcion;

}