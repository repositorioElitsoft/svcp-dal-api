package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.ServicioTrabajo;
import com.elitsoft.servicampo.filter.ServicioTrabajoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad ServicioTrabajo.
 */
@Mapper
public interface ServicioTrabajoMapper {

    /**
     * Agrega un ServicioTrabajo a la base de datos.
     * @param serviciotrabajo La entidad ServicioTrabajo a agregar.
     * @return La clave generada del nuevo registro de ServicioTrabajo.
     */
    Long agregar(ServicioTrabajo serviciotrabajo);

    /**
     * Agrega un ServicioTrabajo a la base de datos.
     * @param serviciotrabajo La entidad ServicioTrabajo a agregar.
     * @return ServicioTrabajo con campo autogenerado.
     */
//    ServicioTrabajo agregar(ServicioTrabajo serviciotrabajo);

    /**
     * Agrega Lote ServicioTrabajo a la base de datos.
     * @param servicioTrabajoLote Lista entidad ServicioTrabajo a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<ServicioTrabajo> servicioTrabajoLote);

    /**
     * Actualiza un ServicioTrabajo en la base de datos.
     * @param serviciotrabajo La entidad ServicioTrabajo a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(ServicioTrabajo serviciotrabajo);

    /**
     * Actualiza Lote ServicioTrabajo a la base de datos.
     * @param servicioTrabajoLote Lista entidad ServicioTrabajo a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<ServicioTrabajo> servicioTrabajoLote);

    /**
     * Elimina un ServicioTrabajo en la base de datos por su clave.
     * @param servicioId La clave de Servicio a eliminar.
     * @param trabajoId La clave de Trabajo a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long servicioId, Long trabajoId);

    /**
     * Elimina Lote ServicioTrabajo en la base de datos.
     * @param servicioTrabajoLote Lista entidad ServicioTrabajo a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<ServicioTrabajo> servicioTrabajoLote);

    /**
     * Encuentra un ServicioTrabajo en la base de datos por su clave.
     * @param servicioId La clave de Servicio a encontrar.
     * @param trabajoId La clave de Trabajo a encontrar.
     * @return La entidad ServicioTrabajo encontrado, o null si no es encontrado.
     */
    ServicioTrabajo encontrarPorClave(Long servicioId, Long trabajoId);

    /**
     * Encuentra lista ServicioTrabajo en la base de datos asociados a #EntityRelacionado#.
     * @param #entityRelacionado#Id La clave de #EntityRelacionado# a encontrar.
     * @param limite si ejerce un limite de registros o no en los resultados
     * @return Lista entidad ServicioTrabajo encontrado, o null si no es encontrado.
     */
    //List<ServicioTrabajo>  encontrarPor#EntityRelacionado#(Long #entityRelacionado#Id, boolean limite);


    /**
     * Obtiene todos los ServicioTrabajo desde la base de datos.
     * @return List<ServicioTrabajo> Una lista de todos los entidades ServicioTrabajo.
     */
    List<ServicioTrabajo> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para ServicioTrabajo
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<ServicioTrabajo> lista de entidades ServicioTrabajo
     */
    List<ServicioTrabajo> filtrar(@Param("filtro") ServicioTrabajoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de ServicioTrabajo
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ServicioTrabajoFiltro filtro);

}