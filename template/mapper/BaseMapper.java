package com.elitsoft.#app_name#.mapper;

import com.elitsoft.#app_name#.domain.entity.#Base#;
import com.elitsoft.#app_name#.filter.#Base#Filtro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad #Base#.
 */
@Mapper
public interface #Base#Mapper {

    /**
     * Agrega un #Base# a la base de datos.
     * @param #base# La entidad #Base# a agregar.
     * @return La clave generada del nuevo registro de #Base#.
     */
    Long agregar(#Base# #base#);

    /**
     * Agrega un #Base# a la base de datos.
     * @param #base# La entidad #Base# a agregar.
     * @return #Base# con campo autogenerado.
     */
    #Base# agregar(#Base# #base#);

    /**
     * Agrega Lote #Base# a la base de datos.
     * @param #base#Lote Lista entidad #Base# a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<#Base#> #base#Lote);

    /**
     * Actualiza un #Base# en la base de datos.
     * @param #base# La entidad #Base# a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(#Base# #base#);

    /**
     * Actualiza Lote #Base# a la base de datos.
     * @param #base#Lote Lista entidad #Base# a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<#Base#> #base#Lote);

    /**
     * Elimina un #Base# en la base de datos por su clave.
     * @param id La clave de #Base# a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote #Base# en la base de datos.
     * @param #base#Lote Lista entidad #Base# a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<#Base#> #base#Lote);

    /**
     * Encuentra un #Base# en la base de datos por su clave.
     * @param id La clave de #Base# a encontrar.
     * @return La entidad #Base# encontrado, o null si no es encontrado.
     */
    #Base# encontrarPorClave(Long id);

    /**
     * Encuentra lista #Base# en la base de datos asociados a #EntityRelacionado#.
     * @param #entityRelacionado#Id La clave de #EntityRelacionado# a encontrar.
     * @param limite si ejerce un limite de registros o no en los resultados
     * @return Lista entidad #Base# encontrado, o null si no es encontrado.
     */
    //List<#Base#>  encontrarPor#EntityRelacionado#(Long #entityRelacionado#Id, boolean limite);


    /**
     * Obtiene todos los #Base# desde la base de datos.
     * @return List<#Base#> Una lista de todos los entidades #Base#.
     */
    List<#Base#> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para #Base#
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<#Base#> lista de entidades #Base#
     */
    List<#Base#> filtrar(@Param("filtro") #Base#Filtro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de #Base#
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") #Base#Filtro filtro);

}