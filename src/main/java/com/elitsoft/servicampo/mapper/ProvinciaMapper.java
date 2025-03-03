package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Provincia;
import com.elitsoft.servicampo.filter.ProvinciaFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Provincia.
 */
@Mapper
public interface ProvinciaMapper {


    /**
     * Agrega un Provincia a la base de datos.
     * @param provincia La entidad Provincia a agregar.
     * @return Provincia con campo autogenerado.
     */
    Provincia agregar(Provincia provincia);

    /**
     * Agrega Lote Provincia a la base de datos.
     * @param provinciaLote Lista entidad Provincia a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Provincia> provinciaLote);

    /**
     * Actualiza un Provincia en la base de datos.
     * @param provincia La entidad Provincia a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Provincia provincia);

    /**
     * Actualiza Lote Provincia a la base de datos.
     * @param provinciaLote Lista entidad Provincia a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Provincia> provinciaLote);

    /**
     * Elimina un Provincia en la base de datos por su clave.
     * @param id La clave de Provincia a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Provincia en la base de datos.
     * @param idLote Lista de claves de entidad Provincia a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Provincia en la base de datos por su clave.
     * @param id La clave de Provincia a encontrar.
     * @return La entidad Provincia encontrado, o null si no es encontrado.
     */
    Provincia encontrarPorClave(Long id);

    /**
     * Obtiene todos los Provincia desde la base de datos.
     * @param regionId  la clave de Region a encontrar.
     * @return List<Provincia> Una lista de todos los entidades Provincia.
     */
    List<Provincia> obtenerTodos(Long regionId);

    /**
     * Hace filtro dinamico y paginacion para Provincia
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Provincia> lista de entidades Provincia
     */
    List<Provincia> filtrar(@Param("filtro") ProvinciaFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Provincia
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ProvinciaFiltro filtro);

}