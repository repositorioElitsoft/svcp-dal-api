package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Zona;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Zona.
 */
@Mapper
public interface ZonaMapper {

    /**
     * Agrega un Zona a la base de datos.
     * @param zona El objecto Zona a agregar.
     * @return La clave generada del nuevo registro de Zona.
     */
    Long agregar(Zona zona);

    /**
     * Actualiza un Zona en la base de datos.
     * @param zona El objeto Zona a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Zona zona);

    /**
     * Elimina un Zona en la base de datos por su clave.
     * @param id La clave de Zona a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Zona en la base de datos por su clave.
     * @param id La clave de Zona a encontrar.
     * @return El objecto Zona encontrado, o null si no es encontrado.
     */
    Zona encontrarPorClave(Long id);

    /**
     * Obtiene todos los Zonas desde la base de datos.
     * @return Una lista de todos los objetos Zona.
     */
    List<Zona> obtenerTodos();

}