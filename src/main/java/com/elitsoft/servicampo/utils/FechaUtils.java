package com.elitsoft.servicampo.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 */
public class FechaUtils {

    /**
     * @param fecha
     * @param formato
     * @return
     */
    public static String formatearFecha(LocalDate fecha, String formato) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        return fecha.format(formateador);
    }

    /**
     * @param fecha1
     * @param fecha2
     * @param unidad
     * @return
     */
    public static long calcularDiferenciaFechas(LocalDate fecha1, LocalDate fecha2, ChronoUnit unidad) {
        return unidad.between(fecha1, fecha2);
    }

}