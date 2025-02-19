package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.EstructuraFormulario;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad EstructuraFormulario.
 */
@Mapper
public interface EstructuraFormularioMapper {

    /**
     * Agrega un EstructuraFormulario a la base de datos.
     * @param estructuraformulario El objecto EstructuraFormulario a agregar.
     * @return La clave generada del nuevo registro de EstructuraFormulario.
     */
    Long agregar(EstructuraFormulario estructuraformulario);

    /**
     * Actualiza un EstructuraFormulario en la base de datos.
     * @param estructuraformulario El objeto EstructuraFormulario a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(EstructuraFormulario estructuraformulario);

    /**
     * Elimina un EstructuraFormulario en la base de datos por su clave.
     * @param id La clave de EstructuraFormulario a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Encuentra un EstructuraFormulario en la base de datos por su clave.
     * @param id La clave de EstructuraFormulario a encontrar.
     * @return El objecto EstructuraFormulario encontrado, o null si no es encontrado.
     */
    EstructuraFormulario encontrarPorClave(Long id);

    /**
     * Obtiene todos los EstructuraFormularios desde la base de datos.
     * @return Una lista de todos los objetos EstructuraFormulario.
     */
    List<EstructuraFormulario> obtenerTodos();

}