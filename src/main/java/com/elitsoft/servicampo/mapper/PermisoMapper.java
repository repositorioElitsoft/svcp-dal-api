package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Permiso;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Permiso.
 */
@Mapper
public interface PermisoMapper {

    /**
     * Agrega un Permiso a la base de datos.
     * @param permiso El objecto Permiso a agregar.
     * @return La clave generada del nuevo registro de Permiso.
     */
    Long agregar(Permiso permiso);

    /**
     * Actualiza un Permiso en la base de datos.
     * @param permiso El objeto Permiso a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Permiso permiso);

    /**
     * Elimina un Permiso en la base de datos por su clave.
     * @param id La clave de Permiso a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Permiso en la base de datos por su clave.
     * @param id La clave de Permiso a encontrar.
     * @return El objecto Permiso encontrado, o null si no es encontrado.
     */
    Permiso encontrarPorClave(Long id);

    /**
     * Obtiene todos los Permisos desde la base de datos.
     * @return Una lista de todos los objetos Permiso.
     */
    List<Permiso> obtenerTodos();

}