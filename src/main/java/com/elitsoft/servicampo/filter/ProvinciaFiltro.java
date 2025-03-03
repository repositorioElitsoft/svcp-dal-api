package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.Comuna;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


@Data
public class ProvinciaFiltro implements Serializable {

    private Long id;
    private String descripcionCiudad;
    private Long regionId;
    private Long comunaId;

}