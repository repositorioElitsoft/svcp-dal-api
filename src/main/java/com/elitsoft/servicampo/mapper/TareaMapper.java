package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.filter.TareaFiltro;
import com.elitsoft.servicampo.domain.entity.Tarea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Tarea.
 */
@Mapper
public interface TareaMapper {

    /**
     * Agrega un Tarea a la base de datos.
     * @param tarea El objecto Tarea a agregar.
     * @return La clave generada del nuevo registro de Tarea.
     */
    Long agregar(Tarea tarea);

    /**
     * Actualiza un Tarea en la base de datos.
     * @param tarea El objeto Tarea a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Tarea tarea);

    /**
     * Elimina un Tarea en la base de datos por su clave.
     * @param id La clave de Tarea a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un Tarea en la base de datos por su clave.
     * @param id La clave de Tarea a encontrar.
     * @return El objecto Tarea encontrado, o null si no es encontrado.
     */
    Tarea encontrarPorClave(Long id);

    /**
     * Obtiene todos los Tareas desde la base de datos.
     * @return Una lista de todos los objetos Tarea.
     */
    List<Tarea> obtenerTodos();


    /**
     * @param filtro
     * @param campoOrden
     * @param direccionOrden
     * @param limite
     * @param desplazamiento
     * @return
     */
    List<Tarea> filtrarTareas(@Param("filtro") TareaFiltro filtro,
                           @Param("campoOrden") String campoOrden,
                           @Param("direccionOrden") String direccionOrden,
                           @Param("limite") int limite,
                           @Param("desplazamiento") int desplazamiento);

    /**
     * @param criteria
     * @return
     */
    int contarFiltroTareas(@Param("filtro") TareaFiltro criteria);

}