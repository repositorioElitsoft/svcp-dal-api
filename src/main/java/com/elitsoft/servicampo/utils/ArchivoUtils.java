package com.elitsoft.servicampo.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 */
public class ArchivoUtils {

    /**
     * @param rutaArchivo
     * @return
     * @throws IOException
     */
    public static String leerArchivo(String rutaArchivo) throws IOException {
        return Files.readString(Paths.get(rutaArchivo));
    }

}