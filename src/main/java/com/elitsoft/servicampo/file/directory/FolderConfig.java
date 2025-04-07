package com.elitsoft.servicampo.file.directory;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FolderConfig {

    @Value("${file.storage.folders.imagenes}")
    private String imagenFolder;

    @Value("${file.storage.folders.evidencias}")
    private String directorio;
}