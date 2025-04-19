package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.dto.core.ServicioDTO;
import com.elitsoft.servicampo.domain.entity.Servicio;
import com.elitsoft.servicampo.filter.ServicioFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Servicio.
 */
@Mapper
public interface ServicioMapper {


    /**
     * Agrega un Servicio a la base de datos.
     *
     * @param servicio La entidad Servicio a agregar.
     * @return Servicio con campo autogenerado.
     */
    Servicio agregar(Servicio servicio);

    /**
     * Agrega Lote Servicio a la base de datos.
     *
     * @param servicioLote Lista entidad Servicio a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Servicio> servicioLote);

    /**
     * Actualiza un Servicio en la base de datos.
     *
     * @param servicio La entidad Servicio a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Servicio servicio);

    /**
     * Actualiza Lote Servicio a la base de datos.
     *
     * @param servicioLote Lista entidad Servicio a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Servicio> servicioLote);

    /**
     * Elimina un Servicio en la base de datos por su clave.
     *
     * @param id La clave de Servicio a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Servicio en la base de datos.
     *
     * @param servicioLote Lista de claves de entidad Servicio a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Servicio> servicioLote);

    /**
     * Encuentra un Servicio en la base de datos por su clave.
     *
     * @param id La clave de Servicio a encontrar.
     * @return La entidad Servicio encontrado, o null si no es encontrado.
     */
    Servicio encontrarPorClave(Long id);

    /**
     * Obtiene todos los Servicio desde la base de datos.
     *
     * @return List<Servicio> Una lista de todos los entidades Servicio.
     */
    List<Servicio> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Servicio
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Servicio> lista de entidades Servicio
     */
    List<Servicio> filtrar(@Param("filtro") ServicioFiltro filtro,
                           @Param("campoOrden") String campoOrden,
                           @Param("direccionOrden") String direccionOrden,
                           @Param("limite") int limite,
                           @Param("desplazamiento") int desplazamiento);

    /**
     * Hace filtro dinamico y paginacion para Servicio y Asignaciones de Trabajos
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<ServicioDTO> lista de DTOs Servicio
     */
    List<ServicioDTO> filtrarAsginacion(@Param("filtro") ServicioFiltro filtro,
                                        @Param("campoOrden") String campoOrden,
                                        @Param("direccionOrden") String direccionOrden,
                                        @Param("limite") int limite,
                                        @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Servicio
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ServicioFiltro filtro);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Servicio y Asignaciones de Trabajos
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrarAsginacion(@Param("filtro") ServicioFiltro filtro);

}