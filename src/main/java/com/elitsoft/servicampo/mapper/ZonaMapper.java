package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Zona;
import com.elitsoft.servicampo.filter.ZonaFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Zona.
 */
@Mapper
public interface ZonaMapper {


    /**
     * Agrega un Zona a la base de datos.
     * @param zona La entidad Zona a agregar.
     * @return Zona con campo autogenerado.
     */
    Zona agregar(Zona zona);

    /**
     * Agrega Lote Zona a la base de datos.
     * @param zonaLote Lista entidad Zona a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Zona> zonaLote);

    /**
     * Actualiza un Zona en la base de datos.
     * @param zona La entidad Zona a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Zona zona);

    /**
     * Actualiza Lote Zona a la base de datos.
     * @param zonaLote Lista entidad Zona a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Zona> zonaLote);

    /**
     * Elimina un Zona en la base de datos por su clave.
     * @param id La clave de Zona a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Zona en la base de datos.
     * @param idLote Lista de claves de entidad Zona a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Zona en la base de datos por su clave.
     * @param id La clave de Zona a encontrar.
     * @return La entidad Zona encontrado, o null si no es encontrado.
     */
    Zona encontrarPorClave(Long id);

    /**
     * Obtiene todos los Zona desde la base de datos.
     * @return List<Zona> Una lista de todos los entidades Zona.
     */
    List<Zona> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Zona
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Zona> lista de entidades Zona
     */
    List<Zona> filtrar(@Param("filtro") ZonaFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Zona
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ZonaFiltro filtro);

}