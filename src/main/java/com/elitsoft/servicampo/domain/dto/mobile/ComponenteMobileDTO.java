package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.entity.EstadoComponente;
import com.elitsoft.servicampo.domain.entity.TipoComponente;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ComponenteMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8531987388066126480L;

    private Long id;
    private TipoComponenteMobileDTO tipoComponente;
    private EstadoComponenteMobileDTO estadoComponente;
    private Date fechaCambio;
    private String codigo;
    private String proveedor;
    private String observacion;
    private String modelo;

}