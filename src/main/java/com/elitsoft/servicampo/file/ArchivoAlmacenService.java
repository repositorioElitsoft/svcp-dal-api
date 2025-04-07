package com.elitsoft.servicampo.file;

public interface ArchivoAlmacenService {
    /**
     *
     * @param nombreContenedor
     * @param nombreArchivo
     * @param content
     */
    void subirArchivo(String nombreContenedor, String nombreArchivo, byte[] content);

    /**
     *
     * @param nombreContenedor
     * @param nombreArchivo
     * @return
     */
    byte[] bajarArchivo(String nombreContenedor, String nombreArchivo);

    /**
     *
     * @param nombreContenedor
     * @param nombreArchivo
     */
    void eliminarArchivo(String nombreContenedor, String nombreArchivo);

    /**
     *
     * @param nombreContenedor
     * @param nombreArchivo
     * @return
     */
    boolean existeArchivo(String nombreContenedor, String nombreArchivo);

    /**
     *
     * @param nombreContenedor
     * @param nombreArchivo
     * @param expirationInMinutes
     * @return
     */
    String generateSignedUrl(String nombreContenedor, String nombreArchivo, int expirationInMinutes);
}
