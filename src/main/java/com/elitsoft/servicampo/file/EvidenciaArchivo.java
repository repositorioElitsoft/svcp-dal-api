package com.elitsoft.servicampo.file;

import com.elitsoft.servicampo.file.impl.EvidenciaArchivoAudioService;
import com.elitsoft.servicampo.file.impl.EvidenciaArchivoImagenService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Getter
@Service
public class EvidenciaArchivo {
    
    private final EvidenciaArchivoImagenService imagenService;
    private final EvidenciaArchivoAudioService audioService;
    
    @Autowired
    public EvidenciaArchivo(EvidenciaArchivoImagenService imagenService, EvidenciaArchivoAudioService audioService) {
        this.imagenService = imagenService;
        this.audioService = audioService;
    }

} 