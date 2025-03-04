package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.Permiso;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Data
public class RoleFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 5809411208518757972L;

    private Long id;
    private String nombreRol;

}