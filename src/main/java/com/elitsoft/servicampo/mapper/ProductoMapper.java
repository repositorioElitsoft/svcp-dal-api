package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Producto;
import com.elitsoft.servicampo.filter.ProductoFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Producto.
 */
@Mapper
public interface ProductoMapper {


    /**
     * Agrega un Producto a la base de datos.
     * @param producto La entidad Producto a agregar.
     * @return Producto con campo autogenerado.
     */
    Producto agregar(Producto producto);

    /**
     * Agrega Lote Producto a la base de datos.
     * @param productoLote Lista entidad Producto a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Producto> productoLote);

    /**
     * Actualiza un Producto en la base de datos.
     * @param producto La entidad Producto a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Producto producto);

    /**
     * Actualiza Lote Producto a la base de datos.
     * @param productoLote Lista entidad Producto a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Producto> productoLote);

    /**
     * Elimina un Producto en la base de datos por su clave.
     * @param id La clave de Producto a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Producto en la base de datos.
     * @param productoLote Lista entidad Producto a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Producto> productoLote);

    /**
     * Encuentra un Producto en la base de datos por su clave.
     * @param id La clave de Producto a encontrar.
     * @return La entidad Producto encontrado, o null si no es encontrado.
     */
    Producto encontrarPorClave(Long id);

    /**
     * Encuentra lista Producto en la base de datos asociados a #EntityRelacionado#.
     * @param #entityRelacionado#Id La clave de #EntityRelacionado# a encontrar.
     * @param limite si ejerce un limite de registros o no en los resultados
     * @return Lista entidad Producto encontrado, o null si no es encontrado.
     */
    //List<Producto>  encontrarPor#EntityRelacionado#(Long #entityRelacionado#Id, boolean limite);


    /**
     * Obtiene todos los Producto desde la base de datos.
     * @return List<Producto> Una lista de todos los entidades Producto.
     */
    List<Producto> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Producto
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Producto> lista de entidades Producto
     */
    List<Producto> filtrar(@Param("filtro") ProductoFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Producto
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") ProductoFiltro filtro);

}