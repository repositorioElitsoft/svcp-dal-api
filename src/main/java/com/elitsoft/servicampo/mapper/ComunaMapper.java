package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Comuna;
import com.elitsoft.servicampo.filter.ComunaFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Comuna.
 */
@Mapper
public interface ComunaMapper {


    /**
     * Agrega un Comuna a la base de datos.
     * @param comuna La entidad Comuna a agregar.
     * @return Comuna con campo autogenerado.
     */
    Comuna agregar(Comuna comuna);

    /**
     * Agrega Lote Comuna a la base de datos.
     * @param comunaLote Lista entidad Comuna a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Comuna> comunaLote);

    /**
     * Actualiza un Comuna en la base de datos.
     * @param comuna La entidad Comuna a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Comuna comuna);

    /**
     * Actualiza Lote Comuna a la base de datos.
     * @param comunaLote Lista entidad Comuna a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Comuna> comunaLote);

    /**
     * Elimina un Comuna en la base de datos por su clave.
     * @param id La clave de Comuna a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Comuna en la base de datos.
     * @param idLote Lista de claves de entidad Comuna a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Comuna en la base de datos por su clave.
     * @param id La clave de Comuna a encontrar.
     * @return La entidad Comuna encontrado, o null si no es encontrado.
     */
    Comuna encontrarPorClave(Long id);

    /**
     * Obtiene todos los Comuna desde la base de datos.
     * @param provinciaId clave de Provincia a la que pertenecen las regiones
     * @return List<Comuna> Una lista de todos los entidades Comuna.
     */
    List<Comuna> obtenerTodos(Long provinciaId);

    /**
     * Hace filtro dinamico y paginacion para Comuna
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Comuna> lista de entidades Comuna
     */
    List<Comuna> filtrar(@Param("filtro") ComunaFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Comuna
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ComunaFiltro filtro);

}