package com.elitsoft.servicampo.utils;


import java.text.Normalizer;

/**
 *
 */
public class CadenaUtils {

    /**
     * @param cadena
     * @return
     */
    public static String normalizarCadena(String cadena) {
        String normalized = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{Mn}", "").toLowerCase().trim();
    }

    /**
     * @param correo
     * @return
     */
    public static boolean correoValido(String correo) {
        return correo != null && correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }


}
