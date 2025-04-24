package com.elitsoft.servicampo.filter;


import lombok.Data;
import java.io.Serial;
import java.io.Serializable;



@Data
public class ComponenteFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 8052504178830918944L;

    private Long id;
    private Long tipoComponenteId;
    private String tipoComponenteNombre;
    private Long estadoComponenteId;
    private String codigo;

    private String observacion;
    private String modelo;
    private String proveedor;
    private String fechaCambio;

}