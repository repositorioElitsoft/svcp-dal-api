package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import com.elitsoft.servicampo.domain.entity.TipoProducto;
import com.elitsoft.servicampo.filter.TipoProductoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoProducto.
 */
@Mapper
public interface TipoProductoMapper {

    /**
     * Agrega un TipoProducto a la base de datos.
     *
     * @param tipoProducto La entidad TipoProducto a agregar.
     * @return TipoProducto con campo autogenerado.
     */
    TipoProducto agregar(TipoProducto tipoProducto);

    /**
     * Agrega Lote TipoProducto a la base de datos.
     *
     * @param tipoProductoLote Lista entidad TipoProducto a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoProducto> tipoProductoLote);

    /**
     * Actualiza un TipoProducto en la base de datos.
     *
     * @param tipoProducto La entidad TipoProducto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoProducto tipoProducto);

    /**
     * Actualiza Lote TipoProducto a la base de datos.
     *
     * @param tipoProductoLote Lista entidad TipoProducto a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoProducto> tipoProductoLote);

    /**
     * Elimina un TipoProducto en la base de datos por su clave.
     *
     * @param id La clave de TipoProducto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote TipoProducto en la base de datos.
     *
     * @param idLote Lista de claves de entidad TipoProducto a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un TipoProducto en la base de datos por su clave.
     *
     * @param id La clave de TipoProducto a encontrar.
     * @return La entidad TipoProducto encontrado, o null si no es encontrado.
     */
    TipoProducto encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoProducto desde la base de datos.
     *
     * @return List<TipoProducto> Una lista de todos los entidades TipoProducto.
     */
    List<TipoProducto> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoProducto
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoProducto> lista de entidades TipoProducto
     */
    List<TipoProducto> filtrar(@Param("filtro") TipoProductoFiltro filtro,
                               @Param("campoOrden") String campoOrden,
                               @Param("direccionOrden") String direccionOrden,
                               @Param("limite") int limite,
                               @Param("desplazamiento") int desplazamiento);

    /**
     * Hace filtro dinamico y paginacion para TipoProducto y Asignacion de Tipos Componentes
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoProducto> lista de entidades TipoProducto
     */
    List<TipoProductoDTO> filtrarAsignacion(@Param("filtro") TipoProductoFiltro filtro,
                                            @Param("campoOrden") String campoOrden,
                                            @Param("direccionOrden") String direccionOrden,
                                            @Param("limite") int limite,
                                            @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoProducto
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoProductoFiltro filtro);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoProducto y Asignacion de Tipos Componentes
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrarAsignacion(@Param("filtro") TipoProductoFiltro filtro);

}