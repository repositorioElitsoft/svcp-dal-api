package com.elitsoft.servicampo.domain.dto.mobile;

import com.elitsoft.servicampo.domain.dto.core.*;
import com.elitsoft.servicampo.domain.entity.Sector;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DireccionMobileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2729102892165140439L;

    private Long id;
    private ClienteDTO cliente;
    private String descripcion;
    private String calle;
    private String numeracion;
    private String referencia;
    private ComunaMobileDTO comuna;
    private SectorMobileDTO sector;
    private List<ContactoMobileDTO> contactos;
    private TipoDireccionMobileDTO tipoDireccion;
    private String imagenPerfil;
    private Double latitud;
    private Double longitud;
    private EstadoMobileDTO estado;
    private String flagEvidencia;

}