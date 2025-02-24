package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Demo;
import com.elitsoft.servicampo.filter.DemoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz  MyBatis Mapper para la entidad Demo.
 */
@Mapper
public interface DemoMapper {

    /**
     * Agrega un Demo a la base de datos.
     * @param demo La entidad Demo a agregar.
     * @return La clave generada del nuevo registro de Demo.
     */
    Long agregar(Demo demo);

    /**
     * Agrega Lote Demo a la base de datos.
     * @param demoLote Lista entidad Demo a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Demo> demoLote);

    /**
     * Actualiza un Demo en la base de datos.
     * @param demo La entidad Demo a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Demo demo);

    /**
     * Actualiza Lote Demo a la base de datos.
     * @param demoLote Lista entidad Demo a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Demo> demoLote);

    /**
     * Elimina un Demo en la base de datos por su clave.
     * @param id La clave de Demo a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Demo en la base de datos.
     * @param idLote Lista de claves de entidad Demo a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Elimina un Demo en la base de datos por su clave.
     * @param id La clave de Demo a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(List<Long>  id);

    /**
     * Encuentra un Demo en la base de datos por su clave.
     * @param id La clave de Demo a encontrar.
     * @return La entidad Demo encontrado, o null si no es encontrado.
     */
    Demo encontrarPorClave(Long id);

    /**
     * Obtiene todos los Demos desde la base de datos.
     * @return List<Demo> Una lista de todos los entidades Demo.
     */
    List<Demo> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Demo
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Demo> lista de entidades Demo
     */
    List<Demo> filtrar(@Param("filtro") DemoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Demo
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") DemoFiltro filtro);

}