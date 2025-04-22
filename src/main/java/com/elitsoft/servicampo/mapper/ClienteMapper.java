package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Cliente;
import com.elitsoft.servicampo.filter.ClienteFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Cliente.
 */
@Mapper
public interface ClienteMapper {


    /**
     * Agrega un Cliente a la base de datos.
     *
     * @param cliente La entidad Cliente a agregar.
     * @return Cliente con campo autogenerado.
     */
    Cliente agregar(Cliente cliente);

    /**
     * Agrega Lote Cliente a la base de datos.
     *
     * @param clienteLote Lista entidad Cliente a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Cliente> clienteLote);

    /**
     * Actualiza un Cliente en la base de datos.
     *
     * @param cliente La entidad Cliente a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Cliente cliente);

    /**
     * Actualiza Lote Cliente a la base de datos.
     *
     * @param clienteLote Lista entidad Cliente a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Cliente> clienteLote);

    /**
     * Elimina un Cliente en la base de datos por su clave.
     *
     * @param id La clave de Cliente a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Cliente en la base de datos.
     *
     * @param idLote Lista de claves de entidad Cliente a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Cliente en la base de datos por su clave.
     *
     * @param id La clave de Cliente a encontrar.
     * @return La entidad Cliente encontrado, o null si no es encontrado.
     */
    Cliente encontrarPorClave(Long id);

    /**
     * Obtiene todos los Cliente desde la base de datos.
     *
     * @return List<Cliente> Una lista de todos los entidades Cliente.
     */
    List<Cliente> obtenerTodos();

    /**
     * Obtiene lista de Direccion de un Cliente en la base de datos por su clave.
     *
     * @param id La clave de Cliente a encontrar.
     * @return La entidad Cliente encontrado, o null si no es encontrado.
     */
    Cliente obtenerDireccionesPorCliente(Long id);

    /**
     * Hace filtro dinamico y paginacion para Cliente
     *
     * @param filtro         clase que tiene los atributos a filtrar
     * @param campoOrden     atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite         atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Cliente> lista de entidades Cliente
     */
    List<Cliente> filtrar(@Param("filtro") ClienteFiltro filtro,
                          @Param("campoOrden") String campoOrden,
                          @Param("direccionOrden") String direccionOrden,
                          @Param("limite") int limite,
                          @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Cliente
     *
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ClienteFiltro filtro);

}