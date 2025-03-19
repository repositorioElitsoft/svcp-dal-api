package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Contrato;
import com.elitsoft.servicampo.filter.ContratoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Contrato.
 */
@Mapper
public interface ContratoMapper {


    /**
     * Agrega un Contrato a la base de datos.
     * @param contrato La entidad Contrato a agregar.
     * @return Contrato con campo autogenerado.
     */
    Contrato agregar(Contrato contrato);


    /**
     * Actualiza un Contrato en la base de datos.
     * @param contrato La entidad Contrato a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Contrato contrato);


    /**
     * Elimina un Contrato en la base de datos por su clave.
     * @param id La clave de Contrato a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Contrato en la base de datos.
     * @param contratoLote Lista entidad Contrato a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Contrato> contratoLote);

    /**
     * Encuentra un Contrato en la base de datos por su clave.
     * @param id La clave de Contrato a encontrar.
     * @return La entidad Contrato encontrado, o null si no es encontrado.
     */
    Contrato encontrarPorClave(Long id);


    /**
     * Obtiene todos los Contrato desde la base de datos.
     * @return List<Contrato> Una lista de todos los entidades Contrato.
     */
    List<Contrato> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Contrato
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Contrato> lista de entidades Contrato
     */
    List<Contrato> filtrar(@Param("filtro") ContratoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Contrato
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ContratoFiltro filtro);

}