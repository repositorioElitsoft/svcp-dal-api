package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoServicio;
import com.elitsoft.servicampo.filter.TipoServicioFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoServicio.
 */
@Mapper
public interface TipoServicioMapper {


    /**
     * Agrega un TipoServicio a la base de datos.
     * @param tipoServicio La entidad TipoServicio a agregar.
     * @return TipoServicio con campo autogenerado.
     */
    TipoServicio agregar(TipoServicio tipoServicio);

    /**
     * Agrega Lote TipoServicio a la base de datos.
     * @param tipoServicioLote Lista entidad TipoServicio a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoServicio> tipoServicioLote);

    /**
     * Actualiza un TipoServicio en la base de datos.
     * @param tipoServicio La entidad TipoServicio a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoServicio tipoServicio);

    /**
     * Actualiza Lote TipoServicio a la base de datos.
     * @param tipoServicioLote Lista entidad TipoServicio a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoServicio> tipoServicioLote);

    /**
     * Elimina un TipoServicio en la base de datos por su clave.
     * @param id La clave de TipoServicio a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote TipoServicio en la base de datos.
     * @param idLote Lista de claves de entidad TipoServicio a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un TipoServicio en la base de datos por su clave.
     * @param id La clave de TipoServicio a encontrar.
     * @return La entidad TipoServicio encontrado, o null si no es encontrado.
     */
    TipoServicio encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoServicio desde la base de datos.
     * @return List<TipoServicio> Una lista de todos los entidades TipoServicio.
     */
    List<TipoServicio> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoServicio
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoServicio> lista de entidades TipoServicio
     */
    List<TipoServicio> filtrar(@Param("filtro") TipoServicioFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoServicio
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoServicioFiltro filtro);

}