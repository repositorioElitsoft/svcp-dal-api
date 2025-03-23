package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleDTO;
import com.elitsoft.servicampo.domain.dto.core.ProductoDTO;
import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ContratoDetalleProductoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -2854297621108969038L;

    //filtros
    private Long contratoDetalle;
    private Long correlativo;
    private Long tipoProducto;
    private Long producto;

    //Ordenamiento
    private Integer valorReferencia;

}