package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ContratoDetalleProductoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleProductoMapStruct;
import com.elitsoft.servicampo.service.core.ContratoDetalleProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  ContratoDetalleProducto.
 */
@Component
public class ContratoDetalleProductoMobileService {

    @Autowired
    private ContratoDetalleProductoMapper contratodetalleproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleProductoService contratodetalleproductoService; //Logica de Negocio del Core Service

    @Autowired
    private ContratoDetalleProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleProductoMobileService.class); //Logback


    /**
     * Agrega un nuevo ContratoDetalleProducto.
     * @param contratodetalleproductoDTO el ContratoDetalleProducto DTO.
     * @return el ContratoDetalleProducto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso ContratoDetalleProducto ya existe.
     */
    public ContratoDetalleProductoDTO agregar(ContratoDetalleProductoDTO contratodetalleproductoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() contratodetalleproducto");

        return contratodetalleproductoService.agregar(contratodetalleproductoDTO);
    }

    /**
     * Agrega Lote nuevos ContratoDetalleProducto.
     * @param contratodetalleproductoDTOLote lista de ContratoDetalleProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso ContratoDetalleProducto ya existe.
     */
    public void agregarLote(List<ContratoDetalleProductoDTO> contratodetalleproductoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() contratodetalleproducto");

        contratodetalleproductoService.agregarLote(contratodetalleproductoDTOLote);
    }

    /**
     * Actualiza un ContratoDetalleProducto existente.
     * @param contratoDetalleId la Clave de Contrato a actualizar.
     * @param tipoProductoId la clave de TipoProducto a actualizar.
     * @param correlativoId la clave del correlativo a actualizar.
     * @param contratodetalleproductoDTO el ContratoDetalleProducto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalleProducto no es encontrado.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleProducto tiene errores.
     */
    public void actualizar(Long contratoDetalleId, Long tipoProductoId, Long correlativoId, ContratoDetalleProductoDTO contratodetalleproductoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() contratodetalleproducto");

        contratodetalleproductoService.actualizar(contratoDetalleId, tipoProductoId, correlativoId,  contratodetalleproductoDTO);
    }

    /**
     * Actualiza Lote de ContratoDetalleProducto existentes.
     * @param contratodetalleproductoDTOLote lista de ContratoDetalleProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleProducto tiene errores.
     */
    public void actualizarLote(List<ContratoDetalleProductoDTO> contratodetalleproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() contratodetalleproducto");

        contratodetalleproductoService.actualizarLote(contratodetalleproductoDTOLote);
    }

    /**
     * Elimina ContratoDetalleProducto por Clave.
     * @param contratoDetalleId la Clave de Contrato a eliminar.
     * @param tipoProductoId la clave de TipoProducto a eliminar.
     * @param correlativoId la clave del correlativo a eliminar
     * @throws RecursoNoEncontradoException si el ContratoDetalleProducto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long contratoDetalleId, Long tipoProductoId, Long correlativoId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() contratodetalleproducto: {}", contratoDetalleId, tipoProductoId, correlativoId);
        contratodetalleproductoService.eliminar(contratoDetalleId, tipoProductoId, correlativoId);
    }

    /**
     * Elimina Lote ContratoDetalleProducto por Clave.
     * @param contratodetalleproductoDTOLote lista de claves de ContratoDetalleProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  ContratoDetalleProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDetalleProductoDTO> contratodetalleproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        contratodetalleproductoService.eliminarLote(contratodetalleproductoDTOLote);
    }

    /**
     * Encuentra un ContratoDetalleProducto por Clave.
     * @param contratoDetalleId la Clave de Contrato a encontrar.
     * @param tipoProductoId la clave de TipoProducto a encontrar.
     * @param correlativoId la clave del correlativo a encontrar.
     * @return el ContratoDetalleProducto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalleProducto no es encontrado.
     */
    public ContratoDetalleProductoDTO encontrarPorClave(Long contratoDetalleId, Long tipoProductoId, Long correlativoId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}, {}, {}", contratoDetalleId, tipoProductoId, correlativoId );
        return contratodetalleproductoService.encontrarPorClave(contratoDetalleId, tipoProductoId, correlativoId );
    }

    /**
     * Obtiene todos los ContratoDetalleProductos.
     * @return lista de todos ContratoDetalleProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDetalleProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return contratodetalleproductoService.obtenerTodos();
    }
}