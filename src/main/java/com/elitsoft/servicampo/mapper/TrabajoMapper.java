package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Trabajo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Trabajo.
 */
@Mapper
public interface TrabajoMapper {

    /**
     * Agrega un Trabajo a la base de datos.
     * @param trabajo El objecto Trabajo a agregar.
     * @return La clave generada del nuevo registro de Trabajo.
     */
    Long agregar(Trabajo trabajo);

    /**
     * Actualiza un Trabajo en la base de datos.
     * @param trabajo El objeto Trabajo a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Trabajo trabajo);

    /**
     * Elimina un Trabajo en la base de datos por su clave.
     * @param id La clave de Trabajo a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Trabajo en la base de datos por su clave.
     * @param id La clave de Trabajo a encontrar.
     * @return El objecto Trabajo encontrado, o null si no es encontrado.
     */
    Trabajo encontrarPorClave(Long id);

    /**
     * Obtiene todos los Trabajos desde la base de datos.
     * @return Una lista de todos los objetos Trabajo.
     */
    List<Trabajo> obtenerTodos();

}