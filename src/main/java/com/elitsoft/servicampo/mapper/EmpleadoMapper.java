package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Empleado;
import com.elitsoft.servicampo.filter.EmpleadoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Empleado.
 */
@Mapper
public interface EmpleadoMapper {


    /**
     * Agrega un Empleado a la base de datos.
     * @param empleado La entidad Empleado a agregar.
     * @return Empleado con campo autogenerado.
     */
    Empleado agregar(Empleado empleado);


    /**
     * Actualiza un Empleado en la base de datos.
     * @param empleado La entidad Empleado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Empleado empleado);

    /**
     * Actualiza clave de un Empleado en la base de datos.
     * @param id La clave de Empleado a actualizar.
     * @param contrasena La clave Empleado a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizarClave(Long id, String contrasena);


    /**
     * Elimina un Empleado en la base de datos por su clave.
     * @param id La clave de Empleado a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);


    /**
     * Encuentra un Empleado en la base de datos por su clave.
     * @param id La clave de Empleado a encontrar.
     * @return La entidad Empleado encontrado, o null si no es encontrado.
     */
    Empleado encontrarPorClave(Long id);

    /**
     * Encuentra lista Empleado en la base de datos asociados a TipoEmpleado.
     * @param tipoEmpleadoId La clave de TipoEmpleado a encontrar.
     * @param limite si ejerce un limite de registros o no en los resultados
     * @return Lista entidad Empleado encontrado, o null si no es encontrado.
     */
    List<Empleado>  encontrarPorTipoEmpleado(Long tipoEmpleadoId, boolean limite);

    /**
     * Encuentra un Empleado en la base de datos por su nombre.
     * @param username del Empleado a encontrar.
     * @return El objecto Empleado encontrado, o null si no es encontrado.
     */
    Empleado encontrarPorNombre(String username);

    /**
     * Obtiene todos los Empleado desde la base de datos.
     * @return List<Empleado> Una lista de todos los entidades Empleado.
     */
    List<Empleado> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Empleado
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Empleado> lista de entidades Empleado
     */
    List<Empleado> filtrar(@Param("filtro") EmpleadoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Empleado
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") EmpleadoFiltro filtro);

}