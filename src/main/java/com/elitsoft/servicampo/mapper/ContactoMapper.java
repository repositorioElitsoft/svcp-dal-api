package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Contacto;
import com.elitsoft.servicampo.filter.ContactoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Contacto.
 */
@Mapper
public interface ContactoMapper {


    /**
     * Agrega un Contacto a la base de datos.
     * @param contacto La entidad Contacto a agregar.
     * @return Contacto con campo autogenerado.
     */
    Contacto agregar(Contacto contacto);

    /**
     * Agrega Lote Contacto a la base de datos.
     * @param contactoLote Lista entidad Contacto a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Contacto> contactoLote);

    /**
     * Actualiza un Contacto en la base de datos.
     * @param contacto La entidad Contacto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Contacto contacto);

    /**
     * Actualiza Lote Contacto a la base de datos.
     * @param contactoLote Lista entidad Contacto a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Contacto> contactoLote);

    /**
     * Elimina un Contacto en la base de datos por su clave.
     * @param id La clave de Contacto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Contacto en la base de datos.
     * @param idLote Lista de claves de entidad Contacto a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Contacto en la base de datos por su clave.
     * @param id La clave de Contacto a encontrar.
     * @return La entidad Contacto encontrado, o null si no es encontrado.
     */
    Contacto encontrarPorClave(Long id);

    /**
     * Obtiene Lista de Direcciones de un Contacto  en la base de datos por su clave
     * @param clienteId La clave de Cliente a encontrar.
     * @param id La clave de Contacto a encontrar.
     * @return La entidad Contacto encontrado, o null si no es encontrado.
     */
    Contacto obtenerDireccionesPorContacto(Long clienteId, Long id);

    /**
     * Obtiene todos los Contacto desde la base de datos.
     * @return List<Contacto> Una lista de todos los entidades Contacto.
     */
    List<Contacto> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Contacto
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Contacto> lista de entidades Contacto
     */
    List<Contacto> filtrar(@Param("filtro") ContactoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Contacto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ContactoFiltro filtro);

}