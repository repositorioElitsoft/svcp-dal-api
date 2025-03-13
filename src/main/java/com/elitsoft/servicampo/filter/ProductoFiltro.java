package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.dto.mobile.EstadoProductoMobileDTO;
import com.elitsoft.servicampo.domain.dto.mobile.TipoComponenteMobileDTO;
import com.elitsoft.servicampo.domain.dto.mobile.TipoProductoMobileDTO;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@Data
public class ProductoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -2297315404449769709L;

    //filtros
    private Long id;
    private String descripcion;
    private Long tipoProducto;
    private Long tipoComponente;
    private Long estadoProducto;

    //ordenamiento
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaFinVigencia;

}