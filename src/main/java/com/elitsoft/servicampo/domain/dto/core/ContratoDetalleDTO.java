package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ContratoDetalleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5663359448525076419L;

    private Long id;
    private ContratoDTO contrato;
    private DireccionDTO direccion;
    private ServicioDTO servicio;

}