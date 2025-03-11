package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class DireccionFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 2572144243617863056L;

    private Long clienteId;
    private String descripcion;
    private String calle;
    private Long comunaId;
    private Long sectorId;
    private Long estadoId;

}