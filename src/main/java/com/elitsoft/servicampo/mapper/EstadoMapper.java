package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Estado;
import com.elitsoft.servicampo.filter.EstadoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Estado.
 */
@Mapper
public interface EstadoMapper {

    /**
     * Agrega un Estado a la base de datos.
     * @param estado La entidad Estado a agregar.
     * @return Estado con campo autogenerado.
     */
    Estado agregar(Estado estado);

    /**
     * Agrega Lote Estado a la base de datos.
     * @param estadoLote Lista entidad Estado a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Estado> estadoLote);

    /**
     * Actualiza un Estado en la base de datos.
     * @param estado La entidad Estado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Estado estado);

    /**
     * Actualiza Lote Estado a la base de datos.
     * @param estadoLote Lista entidad Estado a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Estado> estadoLote);

    /**
     * Elimina un Estado en la base de datos por su clave.
     * @param id La clave de Estado a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Estado en la base de datos.
     * @param idLote Lista de claves de entidad Estado a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Estado en la base de datos por su clave.
     * @param id La clave de Estado a encontrar.
     * @return La entidad Estado encontrado, o null si no es encontrado.
     */
    Estado encontrarPorClave(Long id);

    /**
     * Obtiene todos los Estado desde la base de datos.
     * @return List<Estado> Una lista de todos los entidades Estado.
     */
    List<Estado> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Estado
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Estado> lista de entidades Estado
     */
    List<Estado> filtrar(@Param("filtro") EstadoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Estado
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") EstadoFiltro filtro);

}