package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.AgrupacionComercial;
import com.elitsoft.servicampo.filter.AgrupacionComercialFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad AgrupacionComercial.
 */
@Mapper
public interface AgrupacionComercialMapper {


    /**
     * Agrega un AgrupacionComercial a la base de datos.
     * @param agrupacionComercial La entidad AgrupacionComercial a agregar.
     * @return AgrupacionComercial con campo autogenerado.
     */
    AgrupacionComercial agregar(AgrupacionComercial agrupacionComercial);

    /**
     * Agrega Lote AgrupacionComercial a la base de datos.
     * @param agrupacionComercialLote Lista entidad AgrupacionComercial a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<AgrupacionComercial> agrupacionComercialLote);

    /**
     * Actualiza un AgrupacionComercial en la base de datos.
     * @param agrupacionComercial La entidad AgrupacionComercial a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(AgrupacionComercial agrupacionComercial);

    /**
     * Actualiza Lote AgrupacionComercial a la base de datos.
     * @param agrupacionComercialLote Lista entidad AgrupacionComercial a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<AgrupacionComercial> agrupacionComercialLote);

    /**
     * Elimina un AgrupacionComercial en la base de datos por su clave.
     * @param id La clave de AgrupacionComercial a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote AgrupacionComercial en la base de datos.
     * @param idLote Lista de claves de entidad AgrupacionComercial a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un AgrupacionComercial en la base de datos por su clave.
     * @param id La clave de AgrupacionComercial a encontrar.
     * @return La entidad AgrupacionComercial encontrado, o null si no es encontrado.
     */
    AgrupacionComercial encontrarPorClave(Long id);

    /**
     * Obtiene todos los AgrupacionComercial desde la base de datos.
     * @return List<AgrupacionComercial> Una lista de todos los entidades AgrupacionComercial.
     */
    List<AgrupacionComercial> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para AgrupacionComercial
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<AgrupacionComercial> lista de entidades AgrupacionComercial
     */
    List<AgrupacionComercial> filtrar(@Param("filtro") AgrupacionComercialFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de AgrupacionComercial
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") AgrupacionComercialFiltro filtro);

}