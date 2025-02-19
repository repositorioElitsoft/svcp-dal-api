package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Estado;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Estado.
 */
@Mapper
public interface EstadoMapper {

    /**
     * Agrega un Estado a la base de datos.
     * @param estado El objecto Estado a agregar.
     * @return La clave generada del nuevo registro de Estado.
     */
    Long agregar(Estado estado);

    /**
     * Actualiza un Estado en la base de datos.
     * @param estado El objeto Estado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Estado estado);

    /**
     * Elimina un Estado en la base de datos por su clave.
     * @param id La clave de Estado a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Estado en la base de datos por su clave.
     * @param id La clave de Estado a encontrar.
     * @return El objecto Estado encontrado, o null si no es encontrado.
     */
    Estado encontrarPorClave(Long id);

    /**
     * Obtiene todos los Estados desde la base de datos.
     * @return Una lista de todos los objetos Estado.
     */
    List<Estado> obtenerTodos();

}