package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoCliente;
import com.elitsoft.servicampo.filter.TipoClienteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoCliente.
 */
@Mapper
public interface TipoClienteMapper {


    /**
     * Agrega un TipoCliente a la base de datos.
     * @param tipoCliente La entidad TipoCliente a agregar.
     * @return TipoCliente con campo autogenerado.
     */
    TipoCliente agregar(TipoCliente tipoCliente);

    /**
     * Agrega Lote TipoCliente a la base de datos.
     * @param tipoClienteLote Lista entidad TipoCliente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoCliente> tipoClienteLote);

    /**
     * Actualiza un TipoCliente en la base de datos.
     * @param tipoCliente La entidad TipoCliente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoCliente tipoCliente);

    /**
     * Actualiza Lote TipoCliente a la base de datos.
     * @param tipoClienteLote Lista entidad TipoCliente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoCliente> tipoClienteLote);

    /**
     * Elimina un TipoCliente en la base de datos por su clave.
     * @param id La clave de TipoCliente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote TipoCliente en la base de datos.
     * @param idLote Lista de claves de entidad TipoCliente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un TipoCliente en la base de datos por su clave.
     * @param id La clave de TipoCliente a encontrar.
     * @return La entidad TipoCliente encontrado, o null si no es encontrado.
     */
    TipoCliente encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoCliente desde la base de datos.
     * @return List<TipoCliente> Una lista de todos los entidades TipoCliente.
     */
    List<TipoCliente> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoCliente> lista de entidades TipoCliente
     */
    List<TipoCliente> filtrar(@Param("filtro") TipoClienteFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoClienteFiltro filtro);

}