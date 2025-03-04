package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoDocumentoIdentificacion;
import com.elitsoft.servicampo.filter.TipoDocumentoIdentificacionFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoDocumentoIdentificacion.
 */
@Mapper
public interface TipoDocumentoIdentificacionMapper {

    /**
     * Agrega un TipoDocumentoIdentificacion a la base de datos.
     * @param tipoDocumentoIdentificacion La entidad TipoDocumentoIdentificacion a agregar.
     * @return TipoDocumentoIdentificacion con campo autogenerado.
     */
    TipoDocumentoIdentificacion agregar(TipoDocumentoIdentificacion tipoDocumentoIdentificacion);

    /**
     * Agrega Lote TipoDocumentoIdentificacion a la base de datos.
     * @param tipoDocumentoIdentificacionLote Lista entidad TipoDocumentoIdentificacion a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionLote);

    /**
     * Actualiza un TipoDocumentoIdentificacion en la base de datos.
     * @param tipoDocumentoIdentificacion La entidad TipoDocumentoIdentificacion a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoDocumentoIdentificacion tipoDocumentoIdentificacion);

    /**
     * Actualiza Lote TipoDocumentoIdentificacion a la base de datos.
     * @param tipoDocumentoIdentificacionLote Lista entidad TipoDocumentoIdentificacion a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoDocumentoIdentificacion> tipoDocumentoIdentificacionLote);

    /**
     * Elimina un TipoDocumentoIdentificacion en la base de datos por su clave.
     * @param id La clave de TipoDocumentoIdentificacion a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote TipoDocumentoIdentificacion en la base de datos.
     * @param idLote Lista de claves de entidad TipoDocumentoIdentificacion a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un TipoDocumentoIdentificacion en la base de datos por su clave.
     * @param id La clave de TipoDocumentoIdentificacion a encontrar.
     * @return La entidad TipoDocumentoIdentificacion encontrado, o null si no es encontrado.
     */
    TipoDocumentoIdentificacion encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoDocumentoIdentificacion desde la base de datos.
     * @return List<TipoDocumentoIdentificacion> Una lista de todos los entidades TipoDocumentoIdentificacion.
     */
    List<TipoDocumentoIdentificacion> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoDocumentoIdentificacion
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoDocumentoIdentificacion> lista de entidades TipoDocumentoIdentificacion
     */
    List<TipoDocumentoIdentificacion> filtrar(@Param("filtro") TipoDocumentoIdentificacionFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoDocumentoIdentificacion
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoDocumentoIdentificacionFiltro filtro);

}