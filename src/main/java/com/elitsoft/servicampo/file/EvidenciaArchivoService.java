package com.elitsoft.servicampo.file;

public interface EvidenciaArchivoService {
    /**
     *
     * @param cliente
     * @param direccion
     * @param fecha
     * @param content
     * @param nombreArchivo
     */
    void subir(String cliente, String direccion, String fecha, byte[] content, String nombreArchivo);

    /**
     *
     * @param cliente
     * @param direccion
     * @param fecha
     * @param nombreArchivo
     * @return
     */
    byte[] bajar(String cliente, String direccion, String fecha, String nombreArchivo);

    /**
     *
     * @param cliente
     * @param direccion
     * @param fecha
     * @param nombreArchivo
     */
    void eliminar(String cliente, String direccion, String fecha, String nombreArchivo);

    /**
     *
     * @param cliente
     * @param direccion
     * @param fecha
     * @param nombreArchivo
     * @return
     */
    boolean existe(String cliente, String direccion, String fecha, String nombreArchivo);
}
