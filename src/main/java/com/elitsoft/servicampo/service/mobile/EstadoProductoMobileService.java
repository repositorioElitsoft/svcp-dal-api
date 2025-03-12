package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EstadoProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EstadoProductoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoProductoMapStruct;
import com.elitsoft.servicampo.service.core.EstadoProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  EstadoProducto.
 */
@Component
public class EstadoProductoMobileService {

    @Autowired
    private EstadoProductoMapper estadoproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoProductoService estadoproductoService; //Logica de Negocio del Core Service

    @Autowired
    private EstadoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoProductoMobileService.class); //Logback


    /**
     * Agrega un nuevo EstadoProducto.
     * @param estadoproductoDTO el EstadoProducto DTO.
     * @return el EstadoProducto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso EstadoProducto ya existe.
     */
    public EstadoProductoDTO agregar(EstadoProductoDTO estadoproductoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() estadoproducto");

        return estadoproductoService.agregar(estadoproductoDTO);
    }

    /**
     * Agrega Lote nuevos EstadoProducto.
     * @param estadoproductoDTOLote lista de EstadoProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso EstadoProducto ya existe.
     */
    public void agregarLote(List<EstadoProductoDTO> estadoproductoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() estadoproducto");

        estadoproductoService.agregarLote(estadoproductoDTOLote);
    }

    /**
     * Actualiza un EstadoProducto existente.
     * @param id la Clave de EstadoProducto a actualizar.
     * @param estadoproductoDTO el EstadoProducto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si EstadoProducto no es encontrado.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     */
    public void actualizar(Long id, EstadoProductoDTO estadoproductoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() estadoproducto");

        estadoproductoService.actualizar(id, estadoproductoDTO);
    }

    /**
     * Actualiza Lote de EstadoProducto existentes.
     * @param estadoproductoDTOLote lista de EstadoProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada EstadoProducto tiene errores.
     */
    public void actualizarLote(List<EstadoProductoDTO> estadoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() estadoproducto");

        estadoproductoService.actualizarLote(estadoproductoDTOLote);
    }

    /**
     * Elimina EstadoProducto por Clave.
     * @param id la clave de EstadoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el EstadoProducto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() estadoproducto: {}", id);
        estadoproductoService.eliminar(id);
    }

    /**
     * Elimina Lote EstadoProducto por Clave.
     * @param estadoproductoDTOLote lista de claves de EstadoProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  EstadoProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<EstadoProductoDTO> estadoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        estadoproductoService.eliminarLote(estadoproductoDTOLote);
    }

    /**
     * Encuentra un EstadoProducto por Clave.
     * @param id la clave EstadoProducto a encontrar.
     * @return el EstadoProducto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si EstadoProducto no es encontrado.
     */
    public EstadoProductoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return estadoproductoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los EstadoProductos.
     * @return lista de todos EstadoProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EstadoProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return estadoproductoService.obtenerTodos();
    }
}