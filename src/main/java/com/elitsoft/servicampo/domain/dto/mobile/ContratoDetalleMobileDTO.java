package com.elitsoft.servicampo.domain.dto.mobile;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ContratoDetalleMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7232652166109044525L;

    private Long id;
    private ContratoMobileDTO contrato;
    private DireccionMobileDTO direccion;
    private ServicioMobileDTO servicio;

}