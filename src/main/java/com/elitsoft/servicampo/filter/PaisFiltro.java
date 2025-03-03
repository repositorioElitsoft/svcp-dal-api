package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.Region;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Data
public class PaisFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -6298971301630682050L;

    private Long id;
    private String descripcionPais;
    private Long regioneId;

}