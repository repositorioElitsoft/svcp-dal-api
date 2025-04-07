package com.elitsoft.servicampo.file.impl;

import com.elitsoft.servicampo.file.ArchivoAlmacenService;
import com.elitsoft.servicampo.file.ImagenArchivoService;
import com.elitsoft.servicampo.file.directory.ContenedorDirectorio;
import com.elitsoft.servicampo.file.directory.DirectorioImagenDirectorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service("imagenArchivoProductoService")
public class ImagenArchivoProductoService implements ImagenArchivoService {

    @Autowired
    private final ArchivoAlmacenService archivoAlmacenService;

    @Autowired
    private final ContenedorDirectorio contenedorDirectorio;

    @Autowired
    private final DirectorioImagenDirectorio directorioImagenDirectorio;


    @Autowired
    public ImagenArchivoProductoService(ArchivoAlmacenService archivoAlmacenService, ContenedorDirectorio contenedorDirectorio, DirectorioImagenDirectorio directorioImagenDirectorio) {
        this.archivoAlmacenService = archivoAlmacenService;
        this.contenedorDirectorio = contenedorDirectorio;
        this.directorioImagenDirectorio = directorioImagenDirectorio;
    }

    @Override
    public void subir(byte[] content, String nombreImagen) {
        String objectKey = directorioImagenDirectorio.getDirectorio().concat("/")
                .concat(directorioImagenDirectorio.getProducto()).concat("/")
                .concat(nombreImagen);
        archivoAlmacenService.subirArchivo(contenedorDirectorio.getMainBucket(), objectKey, content);
    }

    @Override
    public byte[] bajar(String nombreImagen) {
        String objectKey = directorioImagenDirectorio.getDirectorio().concat("/")
                .concat(directorioImagenDirectorio.getProducto()).concat("/")
                .concat(nombreImagen);
        return archivoAlmacenService.bajarArchivo(contenedorDirectorio.getMainBucket(), objectKey);
    }

    @Override
    public void eliminar(String nombreImagen) {
        String objectKey = directorioImagenDirectorio.getDirectorio().concat("/")
                .concat(directorioImagenDirectorio.getProducto()).concat("/")
                .concat(nombreImagen);
        archivoAlmacenService.eliminarArchivo(contenedorDirectorio.getMainBucket(), objectKey);
    }

    @Override
    public boolean existe(String nombreImagen) {
        String objectKey = directorioImagenDirectorio.getDirectorio().concat("/")
                .concat(directorioImagenDirectorio.getProducto()).concat("/")
                .concat(nombreImagen);
        return archivoAlmacenService.existeArchivo(contenedorDirectorio.getMainBucket(), objectKey);
    }

}