package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Direccion;
import com.elitsoft.servicampo.filter.DireccionFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Direccion.
 */
@Mapper
public interface DireccionMapper {


    /**
     * Agrega un Direccion a la base de datos.
     * @param direccion La entidad Direccion a agregar.
     * @return Direccion con campo autogenerado.
     */
    Direccion agregar(Direccion direccion);

    /**
     * Agrega Lote Direccion a la base de datos.
     * @param direccionLote Lista entidad Direccion a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Direccion> direccionLote);

    /**
     * Actualiza un Direccion en la base de datos.
     * @param direccion La entidad Direccion a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Direccion direccion);

    /**
     * Actualiza Lote Direccion a la base de datos.
     * @param direccionLote Lista entidad Direccion a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Direccion> direccionLote);

    /**
     * Elimina un Direccion en la base de datos por su clave.
     * @param clienteId La clave de Cliente a eliminar.
     * @param id La clave de Direccion a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long clienteId, Long id);

    /**
     * Elimina Lote Direccion en la base de datos.
     * @param direccionLote Lista de claves de entidad Direccion a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Direccion> direccionLote);

    /**
     * Encuentra un Direccion en la base de datos por su clave.
     * @param clientId La clave de Cliente a encontrar.
     * @param id La clave de Direccion a encontrar.
     * @return La entidad Direccion encontrado sin Contactos, o null si no es encontrado.
     */
    Direccion encontrarPorClave(Long clientId, Long id);

    /**
     * Obtiene Lista de Contactos de una Direccion en la base de datos por su clave
     * @param clienteId La clave de Cliente a encontrar.
     * @param id La clave de Direccion a encontrar.
     * @return entidad Direccion con lista Contactos (ContactoDireccion) asociados encontrados, o null si no es encontrado.
     */
    Direccion obtenerContactosPorDireccion(Long clienteId, Long id);


    /**
     * Hace filtro dinamico y paginacion para Direccion
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Direccion> lista de entidades Direccion
     */
    List<Direccion> filtrar(@Param("filtro") DireccionFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Direccion
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") DireccionFiltro filtro);

}