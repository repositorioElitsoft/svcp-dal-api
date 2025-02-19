package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Empleado;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Empleado.
 */
@Mapper
public interface EmpleadoMapper {

    /**
     * Agrega un Empleado a la base de datos.
     * @param empleado El objecto Empleado a agregar.
     * @return La clave generada del nuevo registro de Empleado.
     */
    Long agregar(Empleado empleado);

    /**
     * Actualiza un Empleado en la base de datos.
     * @param empleado El objeto Empleado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Empleado empleado);

    /**
     * Elimina un Empleado en la base de datos por su clave.
     * @param id La clave de Empleado a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Empleado en la base de datos por su clave.
     * @param id La clave de Empleado a encontrar.
     * @return El objecto Empleado encontrado, o null si no es encontrado.
     */
    Empleado encontrarPorClave(Long id);

    /**
     * Encuentra un Empleado en la base de datos por su nombre.
     * @param username del Empleado a encontrar.
     * @return El objecto Empleado encontrado, o null si no es encontrado.
     */
    Empleado encontrarPorNombre(String username);

    /**
     * Obtiene todos los Empleados desde la base de datos.
     * @return Una lista de todos los objetos Empleado.
     */
    List<Empleado> obtenerTodos();

}