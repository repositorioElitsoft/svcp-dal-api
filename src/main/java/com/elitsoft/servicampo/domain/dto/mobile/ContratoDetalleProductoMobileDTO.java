package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleDTO;
import com.elitsoft.servicampo.domain.dto.core.ProductoDTO;
import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ContratoDetalleProductoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2997593112064224399L;

    private ContratoDetalleMobileDTO contratoDetalle;
    private Long correlativo;
    private TipoProductoMobileDTO tipoProducto;
    private ProductoMobileDTO producto;
    private Integer valorReferencia;

}