package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.TipoEmpleado;
import com.elitsoft.servicampo.filter.TipoEmpleadoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad TipoEmpleado.
 */
@Mapper
public interface TipoEmpleadoMapper {


    /**
     * Agrega un TipoEmpleado a la base de datos.
     * @param tipoEmpleado La entidad TipoEmpleado a agregar.
     * @return TipoEmpleado con campo autogenerado.
     */
    TipoEmpleado agregar(TipoEmpleado tipoEmpleado);

    /**
     * Agrega Lote TipoEmpleado a la base de datos.
     * @param tipoEmpleadoLote Lista entidad TipoEmpleado a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<TipoEmpleado> tipoEmpleadoLote);

    /**
     * Actualiza un TipoEmpleado en la base de datos.
     * @param tipoEmpleado La entidad TipoEmpleado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(TipoEmpleado tipoEmpleado);

    /**
     * Actualiza Lote TipoEmpleado a la base de datos.
     * @param tipoEmpleadoLote Lista entidad TipoEmpleado a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<TipoEmpleado> tipoEmpleadoLote);

    /**
     * Elimina un TipoEmpleado en la base de datos por su clave.
     * @param id La clave de TipoEmpleado a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote TipoEmpleado en la base de datos.
     * @param idLote Lista de claves de entidad TipoEmpleado a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un TipoEmpleado en la base de datos por su clave.
     * @param id La clave de TipoEmpleado a encontrar.
     * @return La entidad TipoEmpleado encontrado, o null si no es encontrado.
     */
    TipoEmpleado encontrarPorClave(Long id);

    /**
     * Obtiene todos los TipoEmpleado desde la base de datos.
     * @return List<TipoEmpleado> Una lista de todos los entidades TipoEmpleado.
     */
    List<TipoEmpleado> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para TipoEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<TipoEmpleado> lista de entidades TipoEmpleado
     */
    List<TipoEmpleado> filtrar(@Param("filtro") TipoEmpleadoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de TipoEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TipoEmpleadoFiltro filtro);

}