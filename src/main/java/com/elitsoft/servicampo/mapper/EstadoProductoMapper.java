package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.EstadoProducto;
import com.elitsoft.servicampo.filter.EstadoProductoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad EstadoProducto.
 */
@Mapper
public interface EstadoProductoMapper {


    /**
     * Agrega un EstadoProducto a la base de datos.
     * @param estadoproducto La entidad EstadoProducto a agregar.
     * @return EstadoProducto con campo autogenerado.
     */
    EstadoProducto agregar(EstadoProducto estadoproducto);

    /**
     * Agrega Lote EstadoProducto a la base de datos.
     * @param estadoProductoLote Lista entidad EstadoProducto a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<EstadoProducto> estadoProductoLote);

    /**
     * Actualiza un EstadoProducto en la base de datos.
     * @param estadoproducto La entidad EstadoProducto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(EstadoProducto estadoproducto);

    /**
     * Actualiza Lote EstadoProducto a la base de datos.
     * @param estadoProductoLote Lista entidad EstadoProducto a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<EstadoProducto> estadoProductoLote);

    /**
     * Elimina un EstadoProducto en la base de datos por su clave.
     * @param id La clave de EstadoProducto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote EstadoProducto en la base de datos.
     * @param estadoProductoLote Lista entidad EstadoProducto a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<EstadoProducto> estadoProductoLote);

    /**
     * Encuentra un EstadoProducto en la base de datos por su clave.
     * @param id La clave de EstadoProducto a encontrar.
     * @return La entidad EstadoProducto encontrado, o null si no es encontrado.
     */
    EstadoProducto encontrarPorClave(Long id);

    /**
     * Obtiene todos los EstadoProducto desde la base de datos.
     * @return List<EstadoProducto> Una lista de todos los entidades EstadoProducto.
     */
    List<EstadoProducto> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para EstadoProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<EstadoProducto> lista de entidades EstadoProducto
     */
    List<EstadoProducto> filtrar(@Param("filtro") EstadoProductoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de EstadoProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") EstadoProductoFiltro filtro);

}