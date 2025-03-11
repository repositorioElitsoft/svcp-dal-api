package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.Estado;
import com.elitsoft.servicampo.domain.entity.TipoServicio;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ServicioFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -8296762160580673150L;

    private Long id;
    private String descripcion;
    private Long estado;
    private Long tipoServicio;


}