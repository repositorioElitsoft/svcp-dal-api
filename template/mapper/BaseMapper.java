package com.elitsoft.#app_name#.mapper;

import com.elitsoft.#app_name#.domain.entity.#Base#;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad #Base#.
 */
@Mapper
public interface #Base#Mapper {

    /**
     * Agrega un #Base# a la base de datos.
     * @param #base# El objecto #Base# a agregar.
     * @return La clave generada del nuevo registro de #Base#.
     */
    Long agregar(#Base# #base#);

    /**
     * Actualiza un #Base# en la base de datos.
     * @param #base# El objeto #Base# a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(#Base# #base#);

    /**
     * Elimina un #Base# en la base de datos por su clave.
     * @param id La clave de #Base# a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un #Base# en la base de datos por su clave.
     * @param id La clave de #Base# a encontrar.
     * @return El objecto #Base# encontrado, o null si no es encontrado.
     */
    #Base# encontrarPorClave(Long id);

    /**
     * Obtiene todos los #Base#s desde la base de datos.
     * @return Una lista de todos los objetos #Base#.
     */
    List<#Base#> obtenerTodos();

}