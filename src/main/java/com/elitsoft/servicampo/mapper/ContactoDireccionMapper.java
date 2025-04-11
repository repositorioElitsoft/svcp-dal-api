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
     * Encuentra los Contactos asociados a Direccion en la base de datos por su clave.
     * @param direccionId La clave de Direccion a encontrar.
     * @param clienteId La clave de Cliente a encontrar.
     * @return lista entidad ContactoDireccion encontrado, o null si no es encontrado.
     */
    ContactoDireccion encontrarDireccionContacto(Long direccionId, Long clienteId);

    /**
     * Encuentra las Direccion asociados a Contactos en la base de datos por su clave.
     * @param contactoId La clave de Contacto a encontrar.
     * @return lista entidad ContactoDireccion encontrado, o null si no es encontrado.
     */
    ContactoDireccion encontrarContactoDireccion(Long contactoId);



}