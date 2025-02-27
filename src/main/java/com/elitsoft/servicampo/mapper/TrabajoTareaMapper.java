package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import com.elitsoft.servicampo.filter.TrabajoTareaFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TrabajoTarea.
 */
@Mapper
public interface TrabajoTareaMapper {

    /**
     * Agrega un TrabajoTarea a la base de datos.
     * @param trabajoTarea La entidad TrabajoTarea a agregar.
     * @return La clave generada del nuevo registro de TrabajoTarea.
     */
    Long agregar(TrabajoTarea trabajoTarea);


    /**
     * Agrega Lote TrabajoTarea a la base de datos.
     * @param trabajoTareaLote Lista entidad TrabajoTarea a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TrabajoTarea> trabajoTareaLote);

    /**
     * Actualiza un TrabajoTarea en la base de datos.
     * @param trabajoTarea La entidad TrabajoTarea a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TrabajoTarea trabajoTarea);

    /**
     * Actualiza Lote TrabajoTarea a la base de datos.
     * @param trabajoTareaLote Lista entidad TrabajoTarea a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TrabajoTarea> trabajoTareaLote);

    /**
     * Elimina un TrabajoTarea en la base de datos por su clave.
     * @param trabajoId La clave de TrabajoTarea a eliminar.
     * @param tareaId La clave de TrabajoTarea a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long trabajoId, Long tareaId);

    /**
     * Elimina Lote TrabajoTarea en la base de datos.
     * @param idLote Lista de claves de entidad TrabajoTarea a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un TrabajoTarea en la base de datos por su clave.
     * @param trabajoId La clave de Trabajo a encontrar.
     * @param tareaId La clave de Tarea a encontrar.
     * @return La entidad TrabajoTarea encontrado, o null si no es encontrado.
     */
    TrabajoTarea encontrarPorClave(Long trabajoId, Long tareaId);

    /**
     * Obtiene todos los TrabajoTarea desde la base de datos.
     * @return List<TrabajoTarea> Una lista de todos los entidades TrabajoTarea.
     */
    List<TrabajoTarea> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TrabajoTarea
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TrabajoTarea> lista de entidades TrabajoTarea
     */
    List<TrabajoTarea> filtrar(@Param("filtro") TrabajoTareaFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TrabajoTarea
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TrabajoTareaFiltro filtro);

}