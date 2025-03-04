package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Role;
import com.elitsoft.servicampo.filter.RoleFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Role.
 */
@Mapper
public interface RoleMapper {


    /**
     * Agrega un Role a la base de datos.
     * @param role La entidad Role a agregar.
     * @return Role con campo autogenerado.
     */
    Role agregar(Role role);

    /**
     * Agrega Lote Role a la base de datos.
     * @param roleLote Lista entidad Role a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Role> roleLote);

    /**
     * Actualiza un Role en la base de datos.
     * @param role La entidad Role a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Role role);

    /**
     * Actualiza Lote Role a la base de datos.
     * @param roleLote Lista entidad Role a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Role> roleLote);

    /**
     * Elimina un Role en la base de datos por su clave.
     * @param id La clave de Role a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Role en la base de datos.
     * @param idLote Lista de claves de entidad Role a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un Role en la base de datos por su clave.
     * @param id La clave de Role a encontrar.
     * @return La entidad Role encontrado, o null si no es encontrado.
     */
    Role encontrarPorClave(Long id);

    /**
     * Obtiene todos los Role desde la base de datos.
     * @return List<Role> Una lista de todos los entidades Role.
     */
    List<Role> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Role
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Role> lista de entidades Role
     */
    List<Role> filtrar(@Param("filtro") RoleFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Role
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") RoleFiltro filtro);

}