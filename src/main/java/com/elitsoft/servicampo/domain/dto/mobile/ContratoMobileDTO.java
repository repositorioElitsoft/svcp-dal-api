package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.entity.Cliente;
import com.elitsoft.servicampo.domain.entity.Contacto;
import com.elitsoft.servicampo.domain.entity.Estado;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ContratoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1381734581097375998L;

    private Long id;
    private Date fechaCreacion;
    private Date fechaInicio;
    private Date fechaFin;
    private EstadoMobileDTO estado;
    private ContactoMobileDTO contacto;
    private ClienteMobileDTO cliente;
    //private List<ContratoDetalle> contratosDetalle;

}