package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.ClienteDTO;
import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.domain.dto.core.DireccionDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class ContactoDireccionMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8372585733704221727L;

    private ContactoMobileDTO contacto;
    private ClienteMobileDTO cliente;
    private DireccionMobileDTO direccion;
    private String rol;
}