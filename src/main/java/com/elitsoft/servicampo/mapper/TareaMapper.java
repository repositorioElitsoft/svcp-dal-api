package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Tarea;
import com.elitsoft.servicampo.filter.TareaFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Tarea.
 */
@Mapper
public interface TareaMapper {

    /**
     * Agrega un Tarea a la base de datos.
     * @param tarea La entidad Tarea a agregar.
     * @return Tarea con campo autogenerado.
     */
    Tarea agregar(Tarea tarea);

    /**
     * Agrega Lote Tarea a la base de datos.
     * @param tareaLote Lista entidad Tarea a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Tarea> tareaLote);

    /**
     * Actualiza un Tarea en la base de datos.
     * @param tarea La entidad Tarea a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Tarea tarea);

    /**
     * Actualiza Lote Tarea a la base de datos.
     * @param tareaLote Lista entidad Tarea a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Tarea> tareaLote);

    /**
     * Elimina un Tarea en la base de datos por su clave.
     * @param id La clave de Tarea a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Tarea en la base de datos.
     * @param idLote Lista de claves de entidad Tarea a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Tarea en la base de datos por su clave.
     * @param id La clave de Tarea a encontrar.
     * @return La entidad Tarea encontrado, o null si no es encontrado.
     */
    Tarea encontrarPorClave(Long id);

    /**
     * Obtiene todos los Tarea desde la base de datos.
     * @return List<Tarea> Una lista de todos los entidades Tarea.
     */
    List<Tarea> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Tarea
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Tarea> lista de entidades Tarea
     */
    List<Tarea> filtrar(@Param("filtro") TareaFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Tarea
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TareaFiltro filtro);

}