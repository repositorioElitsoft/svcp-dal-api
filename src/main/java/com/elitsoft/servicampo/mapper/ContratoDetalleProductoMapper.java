package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.ContratoDetalleProducto;
import com.elitsoft.servicampo.filter.ContratoDetalleProductoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad ContratoDetalleProducto.
 */
@Mapper
public interface ContratoDetalleProductoMapper {


    /**
     * Agrega un ContratoDetalleProducto a la base de datos.
     * @param contratoDetalleProducto La entidad ContratoDetalleProducto a agregar.
     * @return ContratoDetalleProducto con campo autogenerado.
     */
    ContratoDetalleProducto agregar(ContratoDetalleProducto contratoDetalleProducto);

    /**
     * Agrega Lote ContratoDetalleProducto a la base de datos.
     * @param contratoDetalleProductoLote Lista entidad ContratoDetalleProducto a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<ContratoDetalleProducto> contratoDetalleProductoLote);

    /**
     * Actualiza un ContratoDetalleProducto en la base de datos.
     * @param contratoDetalleProducto La entidad ContratoDetalleProducto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(ContratoDetalleProducto contratoDetalleProducto);

    /**
     * Actualiza Lote ContratoDetalleProducto a la base de datos.
     * @param contratoDetalleProductoLote Lista entidad ContratoDetalleProducto a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<ContratoDetalleProducto> contratoDetalleProductoLote);

    /**
     * Elimina un ContratoDetalleProducto en la base de datos por su clave.
     * @param contratoDetalleId la Clave de Contrato a eliminar.
     * @param tipoProductoId la clave de TipoProducto a eliminar.
     * @param correlativoId la clave del correlativo a eliminar
     * @return El numero de registro eliminados.
     */
    int eliminar(@Param("contratoDetalleId") Long contratoDetalleId,
                 @Param("tipoProductoId") Long tipoProductoId,
                 @Param("correlativoId") Long correlativoId);

    /**
     * Elimina Lote ContratoDetalleProducto en la base de datos.
     * @param contratoDetalleProductoLote Lista entidad ContratoDetalleProducto a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<ContratoDetalleProducto> contratoDetalleProductoLote);

    /**
     * Encuentra un ContratoDetalleProducto en la base de datos por su clave.
     * @param contratoDetalleId la Clave de Contrato a encontrar.
     * @param tipoProductoId la clave de TipoProducto a encontrar.
     * @param correlativoId la clave del correlativo a encontrar.
     * @return La entidad ContratoDetalleProducto encontrado, o null si no es encontrado.
     */
    ContratoDetalleProducto encontrarPorClave(@Param("contratoDetalleId") Long contratoDetalleId,
                                              @Param("tipoProductoId") Long tipoProductoId,
                                              @Param("correlativoId") Long correlativoId);

    /**
     * Encuentra lista ContratoDetalleProducto en la base de datos asociados a #EntityRelacionado#.
     * @param #entityRelacionado#Id La clave de #EntityRelacionado# a encontrar.
     * @param limite si ejerce un limite de registros o no en los resultados
     * @return Lista entidad ContratoDetalleProducto encontrado, o null si no es encontrado.
     */
    //List<ContratoDetalleProducto>  encontrarPor#EntityRelacionado#(Long #entityRelacionado#Id, boolean limite);


    /**
     * Obtiene todos los ContratoDetalleProducto desde la base de datos.
     * @return List<ContratoDetalleProducto> Una lista de todos los entidades ContratoDetalleProducto.
     */
    List<ContratoDetalleProducto> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para ContratoDetalleProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<ContratoDetalleProducto> lista de entidades ContratoDetalleProducto
     */
    List<ContratoDetalleProducto> filtrar(@Param("filtro") ContratoDetalleProductoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de ContratoDetalleProducto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ContratoDetalleProductoFiltro filtro);

}