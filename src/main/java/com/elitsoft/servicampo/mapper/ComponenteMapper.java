package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Componente;
import com.elitsoft.servicampo.filter.ComponenteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Componente.
 */
@Mapper
public interface ComponenteMapper {


    /**
     * Agrega un Componente a la base de datos.
     *
     * @param componente La entidad Componente a agregar.
     * @return Componente con campo autogenerado.
     */
    Componente agregar(Componente componente);

    /**
     * Agrega Lote Componente a la base de datos.
     *
     * @param componenteLote Lista entidad Componente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Componente> componenteLote);

    /**
     * Actualiza un Componente en la base de datos.
     *
     * @param componente La entidad Componente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Componente componente);

    /**
     * Actualiza Lote Componente a la base de datos.
     *
     * @param componenteLote Lista entidad Componente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Componente> componenteLote);

    /**
     * Elimina un Componente en la base de datos por su clave.
     *
     * @param id La clave de Componente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Componente en la base de datos.
     *
     * @param componenteLote Lista entidad Componente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Componente> componenteLote);

    /**
     * Encuentra un Componente en la base de datos por su clave.
     *
     * @param id La clave de Componente a encontrar.
     * @return La entidad Componente encontrado, o null si no es encontrado.
     */
    Componente encontrarPorClave(Long id);

    /**
     * Obtiene todos los Componente desde la base de datos.
     *
     * @return List<Componente> Una lista de todos los entidades Componente.
     */
    List<Componente> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Componente
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Componente> lista de entidades Componente
     */
    List<Componente> filtrar(@Param("filtro") ComponenteFiltro filtro,
                             @Param("campoOrden") String campoOrden,
                             @Param("direccionOrden") String direccionOrden,
                             @Param("limite") int limite,
                             @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Componente
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ComponenteFiltro filtro);

}