package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.SegmentacionCliente;
import com.elitsoft.servicampo.filter.SegmentacionClienteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad SegmentacionCliente.
 */
@Mapper
public interface SegmentacionClienteMapper {


    /**
     * Agrega un SegmentacionCliente a la base de datos.
     * @param segmentacioncliente La entidad SegmentacionCliente a agregar.
     * @return SegmentacionCliente con campo autogenerado.
     */
    SegmentacionCliente agregar(SegmentacionCliente segmentacioncliente);

    /**
     * Agrega Lote SegmentacionCliente a la base de datos.
     * @param segmentacionclienteLote Lista entidad SegmentacionCliente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<SegmentacionCliente> segmentacionclienteLote);

    /**
     * Actualiza un SegmentacionCliente en la base de datos.
     * @param segmentacioncliente La entidad SegmentacionCliente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(SegmentacionCliente segmentacioncliente);

    /**
     * Actualiza Lote SegmentacionCliente a la base de datos.
     * @param segmentacionclienteLote Lista entidad SegmentacionCliente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<SegmentacionCliente> segmentacionclienteLote);

    /**
     * Elimina un SegmentacionCliente en la base de datos por su clave.
     * @param id La clave de SegmentacionCliente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote SegmentacionCliente en la base de datos.
     * @param idLote Lista de claves de entidad SegmentacionCliente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un SegmentacionCliente en la base de datos por su clave.
     * @param id La clave de SegmentacionCliente a encontrar.
     * @return La entidad SegmentacionCliente encontrado, o null si no es encontrado.
     */
    SegmentacionCliente encontrarPorClave(Long id);

    /**
     * Obtiene todos los SegmentacionCliente desde la base de datos.
     * @return List<SegmentacionCliente> Una lista de todos los entidades SegmentacionCliente.
     */
    List<SegmentacionCliente> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para SegmentacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<SegmentacionCliente> lista de entidades SegmentacionCliente
     */
    List<SegmentacionCliente> filtrar(@Param("filtro") SegmentacionClienteFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de SegmentacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") SegmentacionClienteFiltro filtro);

}