package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad TrabajoTarea.
 */
@Mapper
public interface TrabajoTareaMapper {

    /**
     * Agrega un TrabajoTarea a la base de datos.
     * @param trabajotarea El objecto TrabajoTarea a agregar.
     * @return La clave generada del nuevo registro de TrabajoTarea.
     */
    Long agregar(TrabajoTarea trabajotarea);

    /**
     * Actualiza un TrabajoTarea en la base de datos.
     * @param trabajotarea El objeto TrabajoTarea a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TrabajoTarea trabajotarea);

    /**
     * Elimina un TrabajoTarea en la base de datos por su clave.
     * @param trabajoId clave compuesta de TrabajoTarea a eliminar.
     * @param tareaId  clave compuesta de TrabajoTarea a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(@Param("trabajoId") Long trabajoId, @Param("tareaId") Long tareaId);


    /**
     * Encuentra un TrabajoTarea en la base de datos por su clave.
     * @param trabajoId clave compuesta de TrabajoTarea a encontrar.
     * @param trabajoId clave compuesta de TrabajoTarea a encontrar.
     * @return El objecto TrabajoTarea encontrado, o null si no es encontrado.
     */
    TrabajoTarea encontrarPorClave(@Param("trabajoId") Long trabajoId, @Param("tareaId") Long tareaId);

    /**
     * Obtiene todos los TrabajoTareas desde la base de datos.
     * @return Una lista de todos los objetos TrabajoTarea.
     */
    List<TrabajoTarea> obtenerTodos();

}