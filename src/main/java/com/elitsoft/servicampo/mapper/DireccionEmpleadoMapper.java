package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.DireccionEmpleado;
import com.elitsoft.servicampo.filter.DireccionEmpleadoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad DireccionEmpleado.
 */
@Mapper
public interface DireccionEmpleadoMapper {

    /**
     * Agrega un DireccionEmpleado a la base de datos.
     * @param direccionEmpleado La entidad DireccionEmpleado a agregar.
     * @return DireccionEmpleado con campo autogenerado.
     */
    DireccionEmpleado agregar(DireccionEmpleado direccionEmpleado);

    /**
     * Agrega Lote DireccionEmpleado a la base de datos.
     * @param direccionEmpleadoLote Lista entidad DireccionEmpleado a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<DireccionEmpleado> direccionEmpleadoLote);

    /**
     * Actualiza un DireccionEmpleado en la base de datos.
     * @param direccionEmpleado La entidad DireccionEmpleado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(DireccionEmpleado direccionEmpleado);

    /**
     * Actualiza Lote DireccionEmpleado a la base de datos.
     * @param direccionEmpleadoLote Lista entidad DireccionEmpleado a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<DireccionEmpleado> direccionEmpleadoLote);

    /**
     * Elimina un DireccionEmpleado en la base de datos por su clave.
     * @param id La clave de DireccionEmpleado a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote DireccionEmpleado en la base de datos.
     * @param idLote Lista de claves de entidad DireccionEmpleado a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un DireccionEmpleado en la base de datos por su clave.
     * @param id La clave de DireccionEmpleado a encontrar.
     * @return La entidad DireccionEmpleado encontrado, o null si no es encontrado.
     */
    DireccionEmpleado encontrarPorClave(Long id);

    /**
     * Obtiene todos los DireccionEmpleado desde la base de datos.
     * @return List<DireccionEmpleado> Una lista de todos los entidades DireccionEmpleado.
     */
    List<DireccionEmpleado> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para DireccionEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<DireccionEmpleado> lista de entidades DireccionEmpleado
     */
    List<DireccionEmpleado> filtrar(@Param("filtro") DireccionEmpleadoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de DireccionEmpleado
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") DireccionEmpleadoFiltro filtro);

}