package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoMapStruct;
import com.elitsoft.servicampo.service.core.TipoProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoProducto.
 */
@Component
public class TipoProductoMobileService {

    @Autowired
    private TipoProductoMapper tipoproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoService tipoproductoService; //Logica de Negocio del Core Service

    @Autowired
    private TipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoMobileService.class); //Logback

    /**
     * Agrega un nuevo TipoProducto.
     * @param tipoproductoDto el TipoProducto DTO.
     * @return el TipoProducto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoProducto ya existe.
     */
    public TipoProductoDTO agregar(TipoProductoDTO tipoproductoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipoproducto");

        return tipoproductoService.agregar(tipoproductoDto);
    }

    /**
     * Agrega Lote nuevos TipoProducto.
     * @param tipoproductoLoteDto lista de TipoProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoProducto ya existe.
     */
    public void agregarLote(List<TipoProductoDTO> tipoproductoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoproducto");

        tipoproductoService.agregarLote(tipoproductoLoteDto);
    }

    /**
     * Actualiza un TipoProducto existente.
     * @param id la Clave de TipoProducto a actualizar.
     * @param tipoproductoDto el TipoProducto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProducto no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     */
    public void actualizar(Long id, TipoProductoDTO tipoproductoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tipoproducto");

        tipoproductoService.actualizar(id, tipoproductoDto);
    }

    /**
     * Actualiza Lote de TipoProducto existentes.
     * @param tipoproductoLoteDto lista de TipoProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProducto tiene errores.
     */
    public void actualizarLote(List<TipoProductoDTO> tipoproductoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoproducto");

        tipoproductoService.actualizarLote(tipoproductoLoteDto);
    }

    /**
     * Elimina TipoProducto por Clave.
     * @param id la clave de TipoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el TipoProducto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoproducto: {}", id);
        tipoproductoService.eliminar(id);
    }

    /**
     * Elimina Lote TipoProducto por Clave.
     * @param idLote lista de claves de TipoProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipoproductoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoProducto por Clave.
     * @param id la clave TipoProducto a encontrar.
     * @return el TipoProducto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProducto no es encontrado.
     */
    public TipoProductoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoproductoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoProductos.
     * @return lista de todos TipoProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoproductoService.obtenerTodos();
    }
}