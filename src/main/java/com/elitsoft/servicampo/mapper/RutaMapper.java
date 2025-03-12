package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Ruta;
import com.elitsoft.servicampo.filter.RutaFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Ruta.
 */
@Mapper
public interface RutaMapper {


    /**
     * Agrega un Ruta a la base de datos.
     * @param ruta La entidad Ruta a agregar.
     * @return Ruta con campo autogenerado.
     */
    Ruta agregar(Ruta ruta);

    /**
     * Agrega Lote Ruta a la base de datos.
     * @param rutaLote Lista entidad Ruta a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Ruta> rutaLote);

    /**
     * Actualiza un Ruta en la base de datos.
     * @param ruta La entidad Ruta a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Ruta ruta);

    /**
     * Actualiza Lote Ruta a la base de datos.
     * @param rutaLote Lista entidad Ruta a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Ruta> rutaLote);

    /**
     * Elimina un Ruta en la base de datos por su clave.
     * @param id La clave de Ruta a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Ruta en la base de datos.
     * @param rutaLote Lista entidad Ruta a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Ruta> rutaLote);

    /**
     * Encuentra un Ruta en la base de datos por su clave.
     * @param id La clave de Ruta a encontrar.
     * @return La entidad Ruta encontrado, o null si no es encontrado.
     */
    Ruta encontrarPorClave(Long id);

    /**
     * Encuentra lista Ruta en la base de datos asociados a #EntityRelacionado#.
     * @param #entityRelacionado#Id La clave de #EntityRelacionado# a encontrar.
     * @param limite si ejerce un limite de registros o no en los resultados
     * @return Lista entidad Ruta encontrado, o null si no es encontrado.
     */
    //List<Ruta>  encontrarPor#EntityRelacionado#(Long #entityRelacionado#Id, boolean limite);


    /**
     * Obtiene todos los Ruta desde la base de datos.
     * @return List<Ruta> Una lista de todos los entidades Ruta.
     */
    List<Ruta> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Ruta
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Ruta> lista de entidades Ruta
     */
    List<Ruta> filtrar(@Param("filtro") RutaFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Ruta
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") RutaFiltro filtro);

}