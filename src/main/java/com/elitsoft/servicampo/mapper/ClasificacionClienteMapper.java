package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.ClasificacionCliente;
import com.elitsoft.servicampo.filter.ClasificacionClienteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad ClasificacionCliente.
 */
@Mapper
public interface ClasificacionClienteMapper {

    /**
     * Agrega un ClasificacionCliente a la base de datos.
     * @param clasificacionCliente La entidad ClasificacionCliente a agregar.
     * @return La clave generada del nuevo registro de ClasificacionCliente.
     */
    Long agregar(ClasificacionCliente clasificacionCliente);

    /**
     * Agrega un ClasificacionCliente a la base de datos.
     * @param clasificacioncliente La entidad ClasificacionCliente a agregar.
     * @return ClasificacionCliente con campo autogenerado.
     */
    // ClasificacionCliente agregar(ClasificacionCliente clasificacioncliente);

    /**
     * Agrega Lote ClasificacionCliente a la base de datos.
     * @param clasificacionClienteLote Lista entidad ClasificacionCliente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<ClasificacionCliente> clasificacionClienteLote);

    /**
     * Actualiza un ClasificacionCliente en la base de datos.
     * @param clasificacionCliente La entidad ClasificacionCliente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(ClasificacionCliente clasificacionCliente);

    /**
     * Actualiza Lote ClasificacionCliente a la base de datos.
     * @param clasificacionClienteLote Lista entidad ClasificacionCliente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<ClasificacionCliente> clasificacionClienteLote);

    /**
     * Elimina un ClasificacionCliente en la base de datos por su clave.
     * @param id La clave de ClasificacionCliente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote ClasificacionCliente en la base de datos.
     * @param idLote Lista de claves de entidad ClasificacionCliente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un ClasificacionCliente en la base de datos por su clave.
     * @param id La clave de ClasificacionCliente a encontrar.
     * @return La entidad ClasificacionCliente encontrado, o null si no es encontrado.
     */
    ClasificacionCliente encontrarPorClave(Long id);

    /**
     * Obtiene todos los ClasificacionCliente desde la base de datos.
     * @return List<ClasificacionCliente> Una lista de todos los entidades ClasificacionCliente.
     */
    List<ClasificacionCliente> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para ClasificacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<ClasificacionCliente> lista de entidades ClasificacionCliente
     */
    List<ClasificacionCliente> filtrar(@Param("filtro") ClasificacionClienteFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de ClasificacionCliente
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ClasificacionClienteFiltro filtro);

}