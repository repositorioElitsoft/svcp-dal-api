package com.elitsoft.servicampo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactoDireccion implements Serializable {

    @Serial
    private static final long serialVersionUID = 8359020129365353109L;

    private Contacto contacto;
    private Cliente cliente;
    private Direccion direccion;
    private String rol;
}