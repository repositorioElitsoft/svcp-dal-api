package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Region;
import com.elitsoft.servicampo.filter.RegionFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Region.
 */
@Mapper
public interface RegionMapper {


    /**
     * Agrega un Region a la base de datos.
     * @param region La entidad Region a agregar.
     * @return Region con campo autogenerado.
     */
    Region agregar(Region region);

    /**
     * Agrega Lote Region a la base de datos.
     * @param regionLote Lista entidad Region a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Region> regionLote);

    /**
     * Actualiza un Region en la base de datos.
     * @param region La entidad Region a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Region region);

    /**
     * Actualiza Lote Region a la base de datos.
     * @param regionLote Lista entidad Region a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Region> regionLote);

    /**
     * Elimina un Region en la base de datos por su clave.
     * @param id La clave de Region a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Region en la base de datos.
     * @param idLote Lista de claves de entidad Region a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Region en la base de datos por su clave.
     * @param id La clave de Region a encontrar.
     * @return La entidad Region encontrado, o null si no es encontrado.
     */
    Region encontrarPorClave(Long id);

    /**
     * Obtiene todos los Region desde la base de datos.
     * @return List<Region> Una lista de todos los entidades Region.
     */

    /**
     * Obtiene todos los Region desde la base de datos.
     * @param paisId la Clave Pais a encontrar.
     * @return List<Region> Una lista de todos los entidades Region.
     */
    List<Region> obtenerTodos(Long paisId);

    /**
     * Hace filtro dinamico y paginacion para Region
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Region> lista de entidades Region
     */
    List<Region> filtrar(@Param("filtro") RegionFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Region
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") RegionFiltro filtro);

}