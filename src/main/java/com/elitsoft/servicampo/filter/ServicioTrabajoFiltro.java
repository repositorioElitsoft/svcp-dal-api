package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.Servicio;
import com.elitsoft.servicampo.domain.entity.Trabajo;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ServicioTrabajoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 6314955343459312806L;

    private Long servicio;
    private Long trabajo;
    private Integer secuencia;

}