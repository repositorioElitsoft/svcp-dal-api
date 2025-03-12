package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoProductoTipoComponente;
import com.elitsoft.servicampo.filter.TipoProductoTipoComponenteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoProductoTipoComponente.
 */
@Mapper
public interface TipoProductoTipoComponenteMapper {

    /**
     * Agrega un TipoProductoTipoComponente a la base de datos.
     * @param tipoProductoTipoComponente La entidad TipoProductoTipoComponente a agregar.
     * @return La clave generada del nuevo registro de TipoProductoTipoComponente.
     */
    Long agregar(TipoProductoTipoComponente tipoProductoTipoComponente);


    /**
     * Agrega Lote TipoProductoTipoComponente a la base de datos.
     * @param tipoProductoTipoComponenteLote Lista entidad TipoProductoTipoComponente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoProductoTipoComponente> tipoProductoTipoComponenteLote);

    /**
     * Actualiza un TipoProductoTipoComponente en la base de datos.
     * @param tipoComponenteId La clave de TipoComponente a actualizar.
     * @param tipoProductoId La clave de TipoProducto a actualizar.
     * @param tipoProductoTipoComponente La entidad TipoProductoTipoComponente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Long tipoComponenteId, Long tipoProductoId, TipoProductoTipoComponente tipoProductoTipoComponente);

    /**
     * Actualiza Lote TipoProductoTipoComponente a la base de datos.
     * @param tipoProductoTipoComponenteLote Lista entidad TipoProductoTipoComponente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoProductoTipoComponente> tipoProductoTipoComponenteLote);

    /**
     * Elimina un TipoProductoTipoComponente en la base de datos por su clave.
     * @param tipoComponenteId La clave de TipoComponente a eliminar.
     * @param tipoProductoId La clave de TipoProducto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long tipoComponenteId, Long tipoProductoId);

    /**
     * Elimina Lote TipoProductoTipoComponente en la base de datos.
     * @param tipoProductoTipoComponenteLote Lista entidad TipoProductoTipoComponente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<TipoProductoTipoComponente> tipoProductoTipoComponenteLote);

    /**
     * Encuentra un TipoProductoTipoComponente en la base de datos por su clave.
     * @param tipoComponenteId La clave de TipoComponente a encontrar.
     * @param tipoProductoId La clave de TipoProducto a encontrar.
     * @return La entidad TipoProductoTipoComponente encontrado, o null si no es encontrado.
     */
    TipoProductoTipoComponente encontrarPorClave(Long tipoComponenteId, Long tipoProductoId);

    /**
     * Encuentra lista TipoProductoTipoComponente en la base de datos asociados a #EntityRelacionado#.
     * @param #entityRelacionado#Id La clave de #EntityRelacionado# a encontrar.
     * @param limite si ejerce un limite de registros o no en los resultados
     * @return Lista entidad TipoProductoTipoComponente encontrado, o null si no es encontrado.
     */
    //List<TipoProductoTipoComponente>  encontrarPor#EntityRelacionado#(Long #entityRelacionado#Id, boolean limite);


    /**
     * Obtiene todos los TipoProductoTipoComponente desde la base de datos.
     * @return List<TipoProductoTipoComponente> Una lista de todos los entidades TipoProductoTipoComponente.
     */
    List<TipoProductoTipoComponente> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoProductoTipoComponente
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoProductoTipoComponente> lista de entidades TipoProductoTipoComponente
     */
    List<TipoProductoTipoComponente> filtrar(@Param("filtro") TipoProductoTipoComponenteFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoProductoTipoComponente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoProductoTipoComponenteFiltro filtro);

}