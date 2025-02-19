package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Role.
 */
@Mapper
public interface RoleMapper {

    /**
     * Agrega un Role a la base de datos.
     * @param role El objecto Role a agregar.
     * @return La clave generada del nuevo registro de Role.
     */
    Long agregar(Role role);

    /**
     * Actualiza un Role en la base de datos.
     * @param role El objeto Role a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Role role);

    /**
     * Elimina un Role en la base de datos por su clave.
     * @param id La clave de Role a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Role en la base de datos por su clave.
     * @param id La clave de Role a encontrar.
     * @return El objecto Role encontrado, o null si no es encontrado.
     */
    Role encontrarPorClave(Long id);

    /**
     * Obtiene todos los Roles desde la base de datos.
     * @return Una lista de todos los objetos Role.
     */
    List<Role> obtenerTodos();

}