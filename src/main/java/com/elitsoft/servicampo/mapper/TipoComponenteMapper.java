package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoComponente;
import com.elitsoft.servicampo.filter.TipoComponenteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoComponente.
 */
@Mapper
public interface TipoComponenteMapper {


    /**
     * Agrega un TipoComponente a la base de datos.
     * @param tipoComponente La entidad TipoComponente a agregar.
     * @return TipoComponente con campo autogenerado.
     */
    TipoComponente agregar(TipoComponente tipoComponente);

    /**
     * Agrega Lote TipoComponente a la base de datos.
     * @param tipoComponenteLote Lista entidad TipoComponente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoComponente> tipoComponenteLote);

    /**
     * Actualiza un TipoComponente en la base de datos.
     * @param tipoComponente La entidad TipoComponente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoComponente tipoComponente);

    /**
     * Actualiza Lote TipoComponente a la base de datos.
     * @param tipoComponenteLote Lista entidad TipoComponente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoComponente> tipoComponenteLote);

    /**
     * Elimina un TipoComponente en la base de datos por su clave.
     * @param id La clave de TipoComponente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote TipoComponente en la base de datos.
     * @param tipoComponenteLote Lista entidad TipoComponente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<TipoComponente> tipoComponenteLote);

    /**
     * Encuentra un TipoComponente en la base de datos por su clave.
     * @param id La clave de TipoComponente a encontrar.
     * @return La entidad TipoComponente encontrado, o null si no es encontrado.
     */
    TipoComponente encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoComponente desde la base de datos.
     * @return List<TipoComponente> Una lista de todos los entidades TipoComponente.
     */
    List<TipoComponente> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoComponente
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoComponente> lista de entidades TipoComponente
     */
    List<TipoComponente> filtrar(@Param("filtro") TipoComponenteFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoComponente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoComponenteFiltro filtro);

}