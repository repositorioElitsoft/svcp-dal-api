package com.elitsoft.servicampo.filter;


import com.elitsoft.servicampo.domain.entity.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
public class EmpleadoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = -1578326802778071578L;

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreUsuario;
    private Long tipoEmpleadoId;
    private Long roleId;
    private Long estadoId;
    private String numero;
    private Character digitoVerificador;


}