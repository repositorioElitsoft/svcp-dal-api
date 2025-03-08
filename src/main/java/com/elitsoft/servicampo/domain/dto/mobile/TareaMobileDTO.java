package com.elitsoft.servicampo.domain.dto.mobile;

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
public class TareaMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2088379524229532803L;

    private Long id;
    private String descripcionTarea;
}