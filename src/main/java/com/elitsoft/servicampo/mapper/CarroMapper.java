package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.Carro;
import com.elitsoft.servicampo.filter.CarroFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad Carro.
 */
@Mapper
public interface CarroMapper {

    /**
     * Agrega un Carro a la base de datos.
     * @param carro La entidad Carro a agregar.
     * @return La clave generada del nuevo registro de Carro.
     */
    Long agregar(Carro carro);


    /**
     * Agrega Lote Carro a la base de datos.
     * @param carroLote Lista entidad Carro a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<Carro> carroLote);

    /**
     * Actualiza un Carro en la base de datos.
     * @param carro La entidad Carro a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(Carro carro);

    /**
     * Actualiza Lote Carro a la base de datos.
     * @param carroLote Lista entidad Carro a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<Carro> carroLote);

    /**
     * Elimina un Carro en la base de datos por su clave.
     * @param id La clave de Carro a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote Carro en la base de datos.
     * @param carroLote Lista entidad Carro a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Carro> carroLote);

    /**
     * Encuentra un Carro en la base de datos por su clave.
     * @param id La clave de Carro a encontrar.
     * @return La entidad Carro encontrado, o null si no es encontrado.
     */
    Carro encontrarPorClave(Long id);

    /**
     * Obtiene todos los Carro desde la base de datos.
     * @return List<Carro> Una lista de todos los entidades Carro.
     */
    List<Carro> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para Carro
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<Carro> lista de entidades Carro
     */
    List<Carro> filtrar(@Param("filtro") CarroFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de Carro
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") CarroFiltro filtro);

}