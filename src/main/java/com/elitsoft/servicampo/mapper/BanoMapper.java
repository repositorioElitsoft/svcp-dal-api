package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Bano;
import com.elitsoft.servicampo.filter.BanoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Bano.
 */
@Mapper
public interface BanoMapper {

    /**
     * Agrega un Bano a la base de datos.
     *
     * @param bano La entidad Bano a agregar.
     * @return La clave generada del nuevo registro de Bano.
     */
    Long agregar(Bano bano);


    /**
     * Agrega Lote Bano a la base de datos.
     *
     * @param banoLote Lista entidad Bano a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Bano> banoLote);

    /**
     * Actualiza un Bano en la base de datos.
     *
     * @param bano La entidad Bano a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Bano bano);

    /**
     * Actualiza Lote Bano a la base de datos.
     *
     * @param banoLote Lista entidad Bano a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Bano> banoLote);

    /**
     * Elimina un Bano en la base de datos por su clave.
     *
     * @param id La clave de Bano a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Bano en la base de datos.
     *
     * @param banoLote Lista entidad Bano a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Bano> banoLote);

    /**
     * Encuentra un Bano en la base de datos por su clave.
     *
     * @param id La clave de Bano a encontrar.
     * @return La entidad Bano encontrado, o null si no es encontrado.
     */
    Bano encontrarPorClave(Long id);

    /**
     * Obtiene todos los Bano desde la base de datos.
     *
     * @return List<Bano> Una lista de todos los entidades Bano.
     */
    List<Bano> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Bano
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Bano> lista de entidades Bano
     */
    List<Bano> filtrar(@Param("filtro") BanoFiltro filtro,
                       @Param("campoOrden") String campoOrden,
                       @Param("direccionOrden") String direccionOrden,
                       @Param("limite") int limite,
                       @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Bano
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") BanoFiltro filtro);

}