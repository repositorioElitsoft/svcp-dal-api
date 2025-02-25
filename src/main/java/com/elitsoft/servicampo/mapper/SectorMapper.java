package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Sector;
import com.elitsoft.servicampo.filter.SectorFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Sector.
 */
@Mapper
public interface SectorMapper {


    /**
     * Agrega un Sector a la base de datos.
     * @param sector La entidad Sector a agregar.
     * @return Sector con campo autogenerado.
     */
     Sector agregar(Sector sector);

    /**
     * Agrega Lote Sector a la base de datos.
     * @param sectorLote Lista entidad Sector a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Sector> sectorLote);

    /**
     * Actualiza un Sector en la base de datos.
     * @param sector La entidad Sector a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Sector sector);

    /**
     * Actualiza Lote Sector a la base de datos.
     * @param sectorLote Lista entidad Sector a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Sector> sectorLote);

    /**
     * Elimina un Sector en la base de datos por su clave.
     * @param id La clave de Sector a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Sector en la base de datos.
     * @param idLote Lista de claves de entidad Sector a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Sector en la base de datos por su clave.
     * @param id La clave de Sector a encontrar.
     * @return La entidad Sector encontrado, o null si no es encontrado.
     */
    Sector encontrarPorClave(Long id);

    /**
     * Obtiene todos los Sector desde la base de datos.
     * @return List<Sector> Una lista de todos los entidades Sector.
     */
    List<Sector> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Sector
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Sector> lista de entidades Sector
     */
    List<Sector> filtrar(@Param("filtro") SectorFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Sector
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") SectorFiltro filtro);

}