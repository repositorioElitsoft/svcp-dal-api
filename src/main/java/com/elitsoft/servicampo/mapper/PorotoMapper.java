package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Poroto;
import com.elitsoft.servicampo.filter.PorotoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Poroto.
 */
@Mapper
public interface PorotoMapper {


    /**
     * Agrega un Poroto a la base de datos.
     * @param poroto La entidad Poroto a agregar.
     * @return Poroto con campo autogenerado.
     */
    Poroto agregar(Poroto poroto);

    /**
     * Agrega Lote Poroto a la base de datos.
     * @param porotoLote Lista entidad Poroto a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Poroto> porotoLote);

    /**
     * Actualiza un Poroto en la base de datos.
     * @param poroto La entidad Poroto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Poroto poroto);

    /**
     * Actualiza Lote Poroto a la base de datos.
     * @param porotoLote Lista entidad Poroto a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Poroto> porotoLote);

    /**
     * Elimina un Poroto en la base de datos por su clave.
     * @param id La clave de Poroto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Poroto en la base de datos.
     * @param idLote Lista de claves de entidad Poroto a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Poroto en la base de datos por su clave.
     * @param id La clave de Poroto a encontrar.
     * @return La entidad Poroto encontrado, o null si no es encontrado.
     */
    Poroto encontrarPorClave(Long id);

    /**
     * Obtiene todos los Poroto desde la base de datos.
     * @return List<Poroto> Una lista de todos los entidades Poroto.
     */
    List<Poroto> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Poroto
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Poroto> lista de entidades Poroto
     */
    List<Poroto> filtrar(@Param("filtro") PorotoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Poroto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") PorotoFiltro filtro);

}