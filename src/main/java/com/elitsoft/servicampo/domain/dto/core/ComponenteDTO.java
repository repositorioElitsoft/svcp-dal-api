package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.EstadoComponente;
import com.elitsoft.servicampo.domain.entity.TipoComponente;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ComponenteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1578120204454313914L;

    private Long id;
    private TipoComponenteDTO tipoComponente;
    private EstadoComponenteDTO estadoComponente;
    private Date fechaCambio;
    private String codigo;
    private String proveedor;
    private String observacion;
    private String modelo;
}