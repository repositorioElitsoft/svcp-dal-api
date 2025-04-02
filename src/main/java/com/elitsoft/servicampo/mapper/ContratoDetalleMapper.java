package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.ContratoDetalle;
import com.elitsoft.servicampo.filter.ContratoDetalleFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad ContratoDetalle.
 */
@Mapper
public interface ContratoDetalleMapper {


    /**
     * Agrega un ContratoDetalle a la base de datos.
     * @param contratoDetalle La entidad ContratoDetalle a agregar.
     * @return ContratoDetalle con campo autogenerado.
     */
    ContratoDetalle agregar(ContratoDetalle contratoDetalle);

    /**
     * Agrega Lote ContratoDetalle a la base de datos.
     * @param contratoDetalleLote Lista entidad ContratoDetalle a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<ContratoDetalle> contratoDetalleLote);

    /**
     * Actualiza un ContratoDetalle en la base de datos.
     * @param contratoDetalle La entidad ContratoDetalle a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(ContratoDetalle contratoDetalle);

    /**
     * Actualiza Lote ContratoDetalle a la base de datos.
     * @param contratoDetalleLote Lista entidad ContratoDetalle a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<ContratoDetalle> contratoDetalleLote);

    /**
     * Elimina un ContratoDetalle en la base de datos por su clave.
     * @param id La clave de ContratoDetalle a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote ContratoDetalle en la base de datos.
     * @param contratoDetalleLote Lista entidad ContratoDetalle a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<ContratoDetalle> contratoDetalleLote);

    /**
     * Encuentra un ContratoDetalle en la base de datos por su clave.
     * @param id La clave de ContratoDetalle a encontrar.
     * @return La entidad ContratoDetalle encontrado, o null si no es encontrado.
     */
    ContratoDetalle encontrarPorClave(Long id);

    /**
     * Obtiene todos los ContratoDetalle desde la base de datos.
     * @return List<ContratoDetalle> Una lista de todos los entidades ContratoDetalle.
     */
    List<ContratoDetalle> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para ContratoDetalle
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<ContratoDetalle> lista de entidades ContratoDetalle
     */
    List<ContratoDetalle> filtrar(@Param("filtro") ContratoDetalleFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de ContratoDetalle
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ContratoDetalleFiltro filtro);

}