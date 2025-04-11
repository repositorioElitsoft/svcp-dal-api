package com.elitsoft.servicampo.domain.dto.core;

import com.elitsoft.servicampo.domain.entity.Cliente;
import com.elitsoft.servicampo.domain.entity.Contacto;
import com.elitsoft.servicampo.domain.entity.Direccion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
public class ContactoDireccionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8372585733704221727L;

    private ContactoDTO contacto;
    private ClienteDTO cliente;
    private DireccionDTO direccion;
    private String rol;
}