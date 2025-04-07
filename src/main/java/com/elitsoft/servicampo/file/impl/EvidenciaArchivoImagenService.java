package com.elitsoft.servicampo.file.impl;

import com.elitsoft.servicampo.file.ArchivoAlmacenService;
import com.elitsoft.servicampo.file.EvidenciaArchivoService;
import com.elitsoft.servicampo.file.directory.ContenedorDirectorio;
import com.elitsoft.servicampo.file.directory.DirectorioEvidenciaDirectorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EvidenciaArchivoImagenService implements EvidenciaArchivoService {

    private final ArchivoAlmacenService archivoAlmacenService;
    private final ContenedorDirectorio contenedorDirectorio;
    private final DirectorioEvidenciaDirectorio directorioEvidenciaDirectorio;

    @Autowired
    public EvidenciaArchivoImagenService(ArchivoAlmacenService archivoAlmacenService, ContenedorDirectorio contenedorDirectorio, DirectorioEvidenciaDirectorio directorioEvidenciaDirectorio) {
        this.archivoAlmacenService = archivoAlmacenService;
        this.contenedorDirectorio = contenedorDirectorio;
        this.directorioEvidenciaDirectorio = directorioEvidenciaDirectorio;
    }

    @Override
    public void subir(String cliente, String direccion, String fecha, byte[] content, String nombreArchivo) {
        String objectKey = this.directorioEvidenciaDirectorio.getDirectorio().concat("/")
                .concat(cliente).concat("/")
                .concat(direccion).concat("/")
                .concat(fecha).concat("/")
                .concat(directorioEvidenciaDirectorio.getImagen()).concat("/")
                .concat(nombreArchivo);
        archivoAlmacenService.subirArchivo(contenedorDirectorio.getMainBucket(), objectKey, content);
    }

    @Override
    public byte[] bajar(String cliente, String direccion, String fecha, String nombreArchivo) {
        String objectKey = this.directorioEvidenciaDirectorio.getDirectorio().concat("/")
                .concat(cliente).concat("/")
                .concat(direccion).concat("/")
                .concat(fecha).concat("/")
                .concat(directorioEvidenciaDirectorio.getImagen()).concat("/")
                .concat(nombreArchivo);
        return archivoAlmacenService.bajarArchivo(contenedorDirectorio.getMainBucket(), objectKey);
    }

    @Override
    public void eliminar(String cliente, String direccion, String fecha, String nombreArchivo) {
        String objectKey = this.directorioEvidenciaDirectorio.getDirectorio().concat("/")
                .concat(cliente).concat("/")
                .concat(direccion).concat("/")
                .concat(fecha).concat("/")
                .concat(directorioEvidenciaDirectorio.getImagen()).concat("/")
                .concat(nombreArchivo);
        archivoAlmacenService.eliminarArchivo(contenedorDirectorio.getMainBucket(), objectKey);
    }

    @Override
    public boolean existe(String cliente, String direccion, String fecha, String nombreArchivo) {
        String objectKey = this.directorioEvidenciaDirectorio.getDirectorio().concat("/")
                .concat(cliente).concat("/")
                .concat(direccion).concat("/")
                .concat(fecha).concat("/")
                .concat(directorioEvidenciaDirectorio.getImagen()).concat("/")
                .concat(nombreArchivo);
        return archivoAlmacenService.existeArchivo(contenedorDirectorio.getMainBucket(), objectKey);
    }


}