package com.elitsoft.servicampo.file.directory;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
public class DirectorioImagenDirectorio {

    @Value("${file.storage.folders.imagenes}")
    private String directorio;

    @Value("${file.storage.folders.imagenes.clientes}")
    private String cliente;

    @Value("${file.storage.folders.imagenes.empleados}")
    private String empleado;

    @Value("${file.storage.folders.imagenes.productos}")
    private String producto;
}
