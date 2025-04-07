package com.elitsoft.servicampo.file;

/**
 *
 */
public interface ImagenArchivoService {

    /**
     * @param content
     * @param nombreImagen
     */
    void subir(byte[] content, String nombreImagen);

    /**
     * @param nombreImagen
     * @return
     */
    byte[] bajar(String nombreImagen);

    /**
     * @param nombreImagen
     */
    void eliminar(String nombreImagen);

    /**
     * @param nombreImagen
     * @return
     */
    boolean existe(String nombreImagen);
}
