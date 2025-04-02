package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoDireccion;
import com.elitsoft.servicampo.filter.TipoDireccionFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoDireccion.
 */
@Mapper
public interface TipoDireccionMapper {


    /**
     * Agrega un TipoDireccion a la base de datos.
     * @param tipoDireccion La entidad TipoDireccion a agregar.
     * @return TipoDireccion con campo autogenerado.
     */
    TipoDireccion agregar(TipoDireccion tipoDireccion);

    /**
     * Agrega Lote TipoDireccion a la base de datos.
     * @param tipoDireccionLote Lista entidad TipoDireccion a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoDireccion> tipoDireccionLote);

    /**
     * Actualiza un TipoDireccion en la base de datos.
     * @param tipoDireccion La entidad TipoDireccion a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoDireccion tipoDireccion);

    /**
     * Actualiza Lote TipoDireccion a la base de datos.
     * @param tipoDireccionLote Lista entidad TipoDireccion a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoDireccion> tipoDireccionLote);

    /**
     * Elimina un TipoDireccion en la base de datos por su clave.
     * @param id La clave de TipoDireccion a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote TipoDireccion en la base de datos.
     * @param idLote Lista de claves de entidad TipoDireccion a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un TipoDireccion en la base de datos por su clave.
     * @param id La clave de TipoDireccion a encontrar.
     * @return La entidad TipoDireccion encontrado, o null si no es encontrado.
     */
    TipoDireccion encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoDireccion desde la base de datos.
     * @return List<TipoDireccion> Una lista de todos los entidades TipoDireccion.
     */
    List<TipoDireccion> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoDireccion
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoDireccion> lista de entidades TipoDireccion
     */
    List<TipoDireccion> filtrar(@Param("filtro") TipoDireccionFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoDireccion
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoDireccionFiltro filtro);

}