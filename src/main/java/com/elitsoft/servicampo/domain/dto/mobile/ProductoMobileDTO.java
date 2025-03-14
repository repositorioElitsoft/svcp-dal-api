package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ProductoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2263120315053237330L;

    private Long id;
    private String descripcion;
    private CarroMobileDTO carro;
    private TipoProductoMobileDTO tipoProducto;
    private TipoComponenteMobileDTO tipoComponente;
    private EstadoProductoMobileDTO estadoProducto;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;

}