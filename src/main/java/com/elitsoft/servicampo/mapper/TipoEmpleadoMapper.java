package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoEmpleado;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad TipoEmpleado.
 */
@Mapper
public interface TipoEmpleadoMapper {

    /**
     * Agrega un TipoEmpleado a la base de datos.
     * @param tipoempleado El objecto TipoEmpleado a agregar.
     * @return La clave generada del nuevo registro de TipoEmpleado.
     */
    Long agregar(TipoEmpleado tipoempleado);

    /**
     * Actualiza un TipoEmpleado en la base de datos.
     * @param tipoempleado El objeto TipoEmpleado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoEmpleado tipoempleado);

    /**
     * Elimina un TipoEmpleado en la base de datos por su clave.
     * @param id La clave de TipoEmpleado a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un TipoEmpleado en la base de datos por su clave.
     * @param id La clave de TipoEmpleado a encontrar.
     * @return El objecto TipoEmpleado encontrado, o null si no es encontrado.
     */
    TipoEmpleado encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoEmpleados desde la base de datos.
     * @return Una lista de todos los objetos TipoEmpleado.
     */
    List<TipoEmpleado> obtenerTodos();

}