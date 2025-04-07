package com.elitsoft.servicampo.file.directory;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
public class DirectorioEvidenciaDirectorio {

    @Value("${file.storage.folders.evidencias}")
    private String directorio;

    @Value("${file.storage.folders.evidencias.imagenes}")
    private String imagen;

    @Value("${file.storage.folders.evidencias.audios}")
    private String audio;
}
