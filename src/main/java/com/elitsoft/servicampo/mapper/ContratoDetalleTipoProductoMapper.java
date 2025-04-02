package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.ContratoDetalleTipoProducto;
import com.elitsoft.servicampo.filter.ContratoDetalleTipoProductoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad ContratoDetalleTipoProducto.
 */
@Mapper
public interface ContratoDetalleTipoProductoMapper {

    /**
     * Agrega un ContratoDetalleTipoProducto a la base de datos.
     * @param contratoDetalleTipoProducto La entidad ContratoDetalleTipoProducto a agregar.
     * @return La clave generada del nuevo registro de ContratoDetalleTipoProducto.
     */
    Long agregar(ContratoDetalleTipoProducto contratoDetalleTipoProducto);

    /**
     * Agrega Lote ContratoDetalleTipoProducto a la base de datos.
     * @param contratoDetalleTipoProductoLote Lista entidad ContratoDetalleTipoProducto a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<ContratoDetalleTipoProducto> contratoDetalleTipoProductoLote);

    /**
     * Actualiza un ContratoDetalleTipoProducto en la base de datos.
     * @param contratoDetalleTipoProducto La entidad ContratoDetalleTipoProducto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(ContratoDetalleTipoProducto contratoDetalleTipoProducto);

    /**
     * Actualiza Lote ContratoDetalleTipoProducto a la base de datos.
     * @param contratoDetalleTipoProductoLote Lista entidad ContratoDetalleTipoProducto a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<ContratoDetalleTipoProducto> contratoDetalleTipoProductoLote);

    /**
     * Elimina un ContratoDetalleTipoProducto en la base de datos por su clave.
     * @param contratoDetalleId La clave de ContratoDetalle a eliminar.
     * @param tipoProductoId La clave de TipoProducto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long contratoDetalleId, Long tipoProductoId);

    /**
     * Elimina Lote ContratoDetalleTipoProducto en la base de datos.
     * @param contratoDetalleTipoProductoLote Lista entidad ContratoDetalleTipoProducto a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<ContratoDetalleTipoProducto> contratoDetalleTipoProductoLote);

    /**
     * Encuentra un ContratoDetalleTipoProducto en la base de datos por su clave.
     * @param contratoDetalleId La clave de ContratoDetalle a encontrar.
     * @param tipoProductoId La clave de TipoProducto a encontrar.
     * @return La entidad ContratoDetalleTipoProducto encontrado, o null si no es encontrado.
     */
    ContratoDetalleTipoProducto encontrarPorClave(Long contratoDetalleId, Long tipoProductoId);

 
    /**
     * Obtiene todos los ContratoDetalleTipoProducto desde la base de datos.
     * @return List<ContratoDetalleTipoProducto> Una lista de todos los entidades ContratoDetalleTipoProducto.
     */
    List<ContratoDetalleTipoProducto> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para ContratoDetalleTipoProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<ContratoDetalleTipoProducto> lista de entidades ContratoDetalleTipoProducto
     */
    List<ContratoDetalleTipoProducto> filtrar(@Param("filtro") ContratoDetalleTipoProductoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de ContratoDetalleTipoProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ContratoDetalleTipoProductoFiltro filtro);

}