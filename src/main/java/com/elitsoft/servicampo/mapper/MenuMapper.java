package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Menu.
 */
@Mapper
public interface MenuMapper {

    /**
     * Agrega un Menu a la base de datos.
     * @param menu El objecto Menu a agregar.
     * @return La clave generada del nuevo registro de Menu.
     */
    Long agregar(Menu menu);

    /**
     * Actualiza un Menu en la base de datos.
     * @param menu El objeto Menu a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Menu menu);

    /**
     * Elimina un Menu en la base de datos por su clave.
     * @param id La clave de Menu a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Menu en la base de datos por su clave.
     * @param id La clave de Menu a encontrar.
     * @return El objecto Menu encontrado, o null si no es encontrado.
     */
    Menu encontrarPorClave(Long id);

    /**
     * Obtiene todos los Menus desde la base de datos.
     * @return Una lista de todos los objetos Menu.
     */
    List<Menu> obtenerTodos();

}