package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.ContactoDireccion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad ContactoDireccion.
 */
@Mapper
public interface ContactoDireccionMapper {

    /**
     * Agrega un ContactoDireccion a la base de datos.
     * @param contactodireccion La entidad ContactoDireccion a agregar.
     * @return La clave generada del nuevo registro de ContactoDireccion.
     */
    Long agregar(ContactoDireccion contactodireccion);


    /**
     * Actualiza un ContactoDireccion en la base de datos.
     * @param contactodireccion La entidad ContactoDireccion a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(ContactoDireccion contactodireccion);


    /**
     * Elimina un ContactoDireccion en la base de datos por su clave.
     * @param contactodireccion clave de ContactoDireccion a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(ContactoDireccion contactodireccion);


    /**
     * Encuentra un ContactoDireccion en la base de datos por su clave.
     * @param id La clave de ContactoDireccion a encontrar.
     * @return La entidad ContactoDireccion encontrado, o null si no es encontrado.
     */
    ContactoDireccion encontrarPorClave(Long id);


    /**
     * Obtiene todos los ContactoDireccion desde la base de datos.
     * @return List<ContactoDireccion> Una lista de todos los entidades ContactoDireccion.
     */
    List<ContactoDireccion> obtenerTodos();


}