package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrabajoMobileDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -3154325653146423023L;

    private Long id;
    private String descripcionTrabajo;
    private List<TrabajoTarea> trabajoTareas;
}