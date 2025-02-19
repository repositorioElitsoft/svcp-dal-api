package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.SubMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad SubMenu.
 */
@Mapper
public interface SubMenuMapper {

    /**
     * Agrega un SubMenu a la base de datos.
     * @param submenu El objecto SubMenu a agregar.
     * @return La clave generada del nuevo registro de SubMenu.
     */
    Long agregar(SubMenu submenu);

    /**
     * Actualiza un SubMenu en la base de datos.
     * @param submenu El objeto SubMenu a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(SubMenu submenu);

    /**
     * Elimina un SubMenu en la base de datos por su clave.
     * @param id La clave de SubMenu a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un SubMenu en la base de datos por su clave.
     * @param id La clave de SubMenu a encontrar.
     * @return El objecto SubMenu encontrado, o null si no es encontrado.
     */
    SubMenu encontrarPorClave(Long id);

    /**
     * Obtiene todos los SubMenus desde la base de datos.
     * @return Una lista de todos los objetos SubMenu.
     */
    List<SubMenu> obtenerTodos();

}