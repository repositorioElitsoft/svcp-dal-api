package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.ContratoDetalle;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ContratoDetalleTipoProductoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -9042750483195150701L;

    //filtros
    private Long contratoDetalle;
    private Long tipoProducto;

    //ordenamiento
    private Long totalProducto;


}