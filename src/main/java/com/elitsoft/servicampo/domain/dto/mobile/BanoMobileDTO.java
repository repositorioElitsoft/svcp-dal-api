package com.elitsoft.servicampo.domain.dto.mobile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class BanoMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -120208078712709733L;

    private ComponenteMobileDTO componente;
    private String marca;
    private String colorPuerta;
    private String colorPared;

}