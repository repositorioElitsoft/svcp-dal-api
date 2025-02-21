package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.ClasificacionCliente;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad ClasificacionCliente.
 */
@Mapper
public interface ClasificacionClienteMapper {

    /**
     * Agrega un ClasificacionCliente a la base de datos.
     * @param clasificacioncliente El objecto ClasificacionCliente a agregar.
     * @return La clave generada del nuevo registro de ClasificacionCliente.
     */
    Long agregar(ClasificacionCliente clasificacioncliente);

    /**
     * Actualiza un ClasificacionCliente en la base de datos.
     * @param clasificacioncliente El objeto ClasificacionCliente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(ClasificacionCliente clasificacioncliente);

    /**
     * Elimina un ClasificacionCliente en la base de datos por su clave.
     * @param id La clave de ClasificacionCliente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un ClasificacionCliente en la base de datos por su clave.
     * @param id La clave de ClasificacionCliente a encontrar.
     * @return El objecto ClasificacionCliente encontrado, o null si no es encontrado.
     */
    ClasificacionCliente encontrarPorClave(Long id);

    /**
     * Obtiene todos los ClasificacionClientes desde la base de datos.
     * @return Una lista de todos los objetos ClasificacionCliente.
     */
    List<ClasificacionCliente> obtenerTodos();

}