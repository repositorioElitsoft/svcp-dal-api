package com.elitsoft.servicampo.mapper;

import com.elitsoft.servicampo.domain.entity.DocumentoIdentificacion;
import com.elitsoft.servicampo.filter.DocumentoIdentificacionFiltro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Interfaz MyBatis Mapper para la entidad DocumentoIdentificacion.
 */
@Mapper
public interface DocumentoIdentificacionMapper {


    /**
     * Agrega un DocumentoIdentificacion a la base de datos.
     * @param documentoIdentificacion La entidad DocumentoIdentificacion a agregar.
     * @return DocumentoIdentificacion con campo autogenerado.
     */
    DocumentoIdentificacion agregar(DocumentoIdentificacion documentoIdentificacion);

    /**
     * Agrega Lote DocumentoIdentificacion a la base de datos.
     * @param documentoIdentificacionLote Lista entidad DocumentoIdentificacion a agregar.
     * @return int cantidad de registros agregados
     */
    int agregarLote(List<DocumentoIdentificacion> documentoIdentificacionLote);

    /**
     * Actualiza un DocumentoIdentificacion en la base de datos.
     * @param documentoIdentificacion La entidad DocumentoIdentificacion a actualizar.
     * @return El numero de registro actualizados.
     */
    int actualizar(DocumentoIdentificacion documentoIdentificacion);

    /**
     * Actualiza Lote DocumentoIdentificacion a la base de datos.
     * @param documentoIdentificacionLote Lista entidad DocumentoIdentificacion a agregar.
     * @return int cantidad de registros actualizados.
     */
    int actualizarLote(List<DocumentoIdentificacion> documentoIdentificacionLote);

    /**
     * Elimina un DocumentoIdentificacion en la base de datos por su clave.
     * @param id La clave de DocumentoIdentificacion a eliminar.
     * @return El numero de registro eliminados.
     */
    int eliminar(Long id);

    /**
     * Elimina Lote DocumentoIdentificacion en la base de datos.
     * @param idLote Lista de claves de entidad DocumentoIdentificacion a eliminar.
     * @return int cantidad de registros eliminados.
     */
    int eliminarLote(List<Long> idLote);

    /**
     * Encuentra un DocumentoIdentificacion en la base de datos por su clave.
     * @param id La clave de DocumentoIdentificacion a encontrar.
     * @return La entidad DocumentoIdentificacion encontrado, o null si no es encontrado.
     */
    DocumentoIdentificacion encontrarPorClave(Long id);

    /**
     * Encuentra un DocumentoIdentificacion en la base de datos por su clave.
     * @param id La clave de DocumentoIdentificacion a encontrar.
     * @return La entidad DocumentoIdentificacion encontrado, o null si no es encontrado.
     */

    /**
     * Encuentra un DocumentoIdentificacion en la base de datos por su numero y opcional digitoVerificador.
     * @param numero documento de identificacion.
     * @param digitoVerificador digito verificador.
     * @return La entidad DocumentoIdentificacion encontrado, o null si no es encontrado.
     */
    DocumentoIdentificacion encontrarPorIndentificacion(String numero, Character digitoVerificador);




    /**
     * Obtiene todos los DocumentoIdentificacion desde la base de datos.
     * @return List<DocumentoIdentificacion> Una lista de todos los entidades DocumentoIdentificacion.
     */
    List<DocumentoIdentificacion> obtenerTodos();

    /**
     * Hace filtro dinamico y paginacion para DocumentoIdentificacion
     * @param filtro clase que tiene los atributos a filtrar
     * @param campoOrden atributo que define el ordern del filtro
     * @param direccionOrden atributo que define la direccion del filtro
     * @param limite atributo que define el limite de registros por pagina del filtro
     * @param desplazamiento atributo que define la pagina del filtro
     * @return List<DocumentoIdentificacion> lista de entidades DocumentoIdentificacion
     */
    List<DocumentoIdentificacion> filtrar(@Param("filtro") DocumentoIdentificacionFiltro filtro,
                              @Param("campoOrden") String campoOrden,
                              @Param("direccionOrden") String direccionOrden,
                              @Param("limite") int limite,
                              @Param("desplazamiento") int desplazamiento);

    /**
     * Cuenta los registros que coinciden con el filtro dinamico de DocumentoIdentificacion
     * @param filtro clase que tiene los atributos a filtrar
     * @return int cantidad de registros que retorna el filtro
     */
    int contarFiltrar(@Param("filtro") DocumentoIdentificacionFiltro filtro);

}