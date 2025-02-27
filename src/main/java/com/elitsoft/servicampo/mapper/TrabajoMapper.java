package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Trabajo;
import com.elitsoft.servicampo.filter.TrabajoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Trabajo.
 */
@Mapper
public interface TrabajoMapper {


    /**
     * Agrega un Trabajo a la base de datos.
     * @param trabajo La entidad Trabajo a agregar.
     * @return Trabajo con campo autogenerado.
     */
    Trabajo agregar(Trabajo trabajo);

    /**
     * Agrega Lote Trabajo a la base de datos.
     * @param trabajoLote Lista entidad Trabajo a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Trabajo> trabajoLote);

    /**
     * Actualiza un Trabajo en la base de datos.
     * @param trabajo La entidad Trabajo a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Trabajo trabajo);

    /**
     * Actualiza Lote Trabajo a la base de datos.
     * @param trabajoLote Lista entidad Trabajo a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Trabajo> trabajoLote);

    /**
     * Elimina un Trabajo en la base de datos por su clave.
     * @param id La clave de Trabajo a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Trabajo en la base de datos.
     * @param idLote Lista de claves de entidad Trabajo a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Trabajo en la base de datos por su clave.
     * @param id La clave de Trabajo a encontrar.
     * @return La entidad Trabajo encontrado, o null si no es encontrado.
     */
    Trabajo encontrarPorClave(Long id);

    /**
     * Obtiene todos los Trabajo desde la base de datos.
     * @return List<Trabajo> Una lista de todos los entidades Trabajo.
     */
    List<Trabajo> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Trabajo
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Trabajo> lista de entidades Trabajo
     */
    List<Trabajo> filtrar(@Param("filtro") TrabajoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Trabajo
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") TrabajoFiltro filtro);

}