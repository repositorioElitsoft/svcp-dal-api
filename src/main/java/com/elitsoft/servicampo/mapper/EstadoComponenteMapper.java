package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.EstadoComponente;
import com.elitsoft.servicampo.filter.EstadoComponenteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad EstadoComponente.
 */
@Mapper
public interface EstadoComponenteMapper {


    /**
     * Agrega un EstadoComponente a la base de datos.
     *
     * @param estadocomponente La entidad EstadoComponente a agregar.
     * @return EstadoComponente con campo autogenerado.
     */
    EstadoComponente agregar(EstadoComponente estadocomponente);

    /**
     * Agrega Lote EstadoComponente a la base de datos.
     *
     * @param estadoComponenteLote Lista entidad EstadoComponente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<EstadoComponente> estadoComponenteLote);

    /**
     * Actualiza un EstadoComponente en la base de datos.
     *
     * @param estadoComponente La entidad EstadoComponente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(EstadoComponente estadoComponente);

    /**
     * Actualiza Lote EstadoComponente a la base de datos.
     *
     * @param estadoComponenteLote Lista entidad EstadoComponente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<EstadoComponente> estadoComponenteLote);

    /**
     * Elimina un EstadoComponente en la base de datos por su clave.
     *
     * @param id La clave de EstadoComponente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote EstadoComponente en la base de datos.
     *
     * @param estadoComponenteLote Lista entidad EstadoComponente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<EstadoComponente> estadoComponenteLote);

    /**
     * Encuentra un EstadoComponente en la base de datos por su clave.
     *
     * @param id La clave de EstadoComponente a encontrar.
     * @return La entidad EstadoComponente encontrado, o null si no es encontrado.
     */
    EstadoComponente encontrarPorClave(Long id);

    /**
     * Obtiene todos los EstadoComponente desde la base de datos.
     *
     * @return List<EstadoComponente> Una lista de todos los entidades EstadoComponente.
     */
    List<EstadoComponente> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para EstadoComponente
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<EstadoComponente> lista de entidades EstadoComponente
     */
    List<EstadoComponente> filtrar(@Param("filtro") EstadoComponenteFiltro filtro,
                                   @Param("campoOrden") String campoOrden,
                                   @Param("direccionOrden") String direccionOrden,
                                   @Param("limite") int limite,
                                   @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de EstadoComponente
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") EstadoComponenteFiltro filtro);

}