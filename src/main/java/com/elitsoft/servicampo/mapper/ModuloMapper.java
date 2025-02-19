package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Modulo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Modulo.
 */
@Mapper
public interface ModuloMapper {

    /**
     * Agrega un Modulo a la base de datos.
     * @param modulo El objecto Modulo a agregar.
     * @return La clave generada del nuevo registro de Modulo.
     */
    Long agregar(Modulo modulo);

    /**
     * Actualiza un Modulo en la base de datos.
     * @param modulo El objeto Modulo a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Modulo modulo);

    /**
     * Elimina un Modulo en la base de datos por su clave.
     * @param id La clave de Modulo a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Modulo en la base de datos por su clave.
     * @param id La clave de Modulo a encontrar.
     * @return El objecto Modulo encontrado, o null si no es encontrado.
     */
    Modulo encontrarPorClave(Long id);

    /**
     * Obtiene todos los Modulos desde la base de datos.
     * @return Una lista de todos los objetos Modulo.
     */
    List<Modulo> obtenerTodos();

}