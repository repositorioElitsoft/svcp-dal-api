package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoProducto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad TipoProducto.
 */
@Mapper
public interface TipoProductoMapper {

    /**
     * Agrega un TipoProducto a la base de datos.
     * @param tipoproducto El objecto TipoProducto a agregar.
     * @return La clave generada del nuevo registro de TipoProducto.
     */
    Long agregar(TipoProducto tipoproducto);

    /**
     * Actualiza un TipoProducto en la base de datos.
     * @param tipoproducto El objeto TipoProducto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoProducto tipoproducto);

    /**
     * Elimina un TipoProducto en la base de datos por su clave.
     * @param id La clave de TipoProducto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un TipoProducto en la base de datos por su clave.
     * @param id La clave de TipoProducto a encontrar.
     * @return El objecto TipoProducto encontrado, o null si no es encontrado.
     */
    TipoProducto encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoProductos desde la base de datos.
     * @return Una lista de todos los objetos TipoProducto.
     */
    List<TipoProducto> obtenerTodos();

}