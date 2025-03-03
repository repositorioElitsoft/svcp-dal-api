package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Pais;
import com.elitsoft.servicampo.filter.PaisFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Pais.
 */
@Mapper
public interface PaisMapper {

    /**
     * Agrega un Pais a la base de datos.
     * @param pais La entidad Pais a agregar.
     * @return Pais con campo autogenerado.
     */
    Pais agregar(Pais pais);

    /**
     * Agrega Lote Pais a la base de datos.
     * @param paisLote Lista entidad Pais a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Pais> paisLote);

    /**
     * Actualiza un Pais en la base de datos.
     * @param pais La entidad Pais a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Pais pais);

    /**
     * Actualiza Lote Pais a la base de datos.
     * @param paisLote Lista entidad Pais a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Pais> paisLote);

    /**
     * Elimina un Pais en la base de datos por su clave.
     * @param id La clave de Pais a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Pais en la base de datos.
     * @param idLote Lista de claves de entidad Pais a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Pais en la base de datos por su clave.
     * @param id La clave de Pais a encontrar.
     * @return La entidad Pais encontrado, o null si no es encontrado.
     */
    Pais encontrarPorClave(Long id);

    /**
     * Obtiene todos los Pais desde la base de datos.
     * @return List<Pais> Una lista de todos los entidades Pais.
     */
    List<Pais> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Pais
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Pais> lista de entidades Pais
     */
    List<Pais> filtrar(@Param("filtro") PaisFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Pais
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") PaisFiltro filtro);

}