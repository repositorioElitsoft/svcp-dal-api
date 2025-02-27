package com.elitsoft.servicampo.domain.dto.core;

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
public class TareaDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -626564715792554029L;

    private Long id;
    private String descripcionTarea;
}