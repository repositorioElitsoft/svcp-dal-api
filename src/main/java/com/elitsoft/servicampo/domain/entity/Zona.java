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
public class Zona implements Serializable {

    @Serial
    private static final long serialVersionUID = 9142407512418013683L;

    private Long id;
    private String descripcionZona;
}
