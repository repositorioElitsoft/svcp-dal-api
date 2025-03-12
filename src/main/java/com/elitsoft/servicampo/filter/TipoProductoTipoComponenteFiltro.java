package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.TipoComponente;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class TipoProductoTipoComponenteFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 7992368270369071859L;

    //filtros
    private Long tipoComponente;
    private Long tipoProducto;

    //ordernamiento
    private int cantidad;

}