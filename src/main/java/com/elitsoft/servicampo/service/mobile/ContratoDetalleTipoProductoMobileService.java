package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleTipoProductoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ContratoDetalleTipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleTipoProductoMapStruct;
import com.elitsoft.servicampo.service.core.ContratoDetalleTipoProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  ContratoDetalleTipoProducto.
 */
@Component
public class ContratoDetalleTipoProductoMobileService {

    @Autowired
    private ContratoDetalleTipoProductoMapper contratodetalletipoproductoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleTipoProductoService contratodetalletipoproductoService; //Logica de Negocio del Core Service

    @Autowired
    private ContratoDetalleTipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleTipoProductoMobileService.class); //Logback

    /**
     * Agrega un nuevo ContratoDetalleTipoProducto.
     * @param contratodetalletipoproductoDTO el ContratoDetalleTipoProducto DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleTipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso ContratoDetalleTipoProducto ya existe.
     */
    public void agregar(ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() contratodetalletipoproducto");

        contratodetalletipoproductoService.agregar(contratodetalletipoproductoDTO);
    }


    /**
     * Agrega Lote nuevos ContratoDetalleTipoProducto.
     * @param contratodetalletipoproductoDTOLote lista de ContratoDetalleTipoProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleTipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso ContratoDetalleTipoProducto ya existe.
     */
    public void agregarLote(List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() contratodetalletipoproducto");

        contratodetalletipoproductoService.agregarLote(contratodetalletipoproductoDTOLote);
    }

    /**
     * Actualiza un ContratoDetalleTipoProducto existente.
     * @param contratoDetalleId la clave de ContratoDetalle a actualizar.
     * @param tipoProductoId la clave de TipoProducto a actualizar.
     * @param contratodetalletipoproductoDTO el ContratoDetalleTipoProducto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalleTipoProducto no es encontrado.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleTipoProducto tiene errores.
     */
    public void actualizar(Long contratoDetalleId, Long tipoProductoId, ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() contratodetalletipoproducto");

        contratodetalletipoproductoService.actualizar(contratoDetalleId, tipoProductoId, contratodetalletipoproductoDTO);
    }

    /**
     * Actualiza Lote de ContratoDetalleTipoProducto existentes.
     * @param contratodetalletipoproductoDTOLote lista de ContratoDetalleTipoProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleTipoProducto tiene errores.
     */
    public void actualizarLote(List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() contratodetalletipoproducto");

        contratodetalletipoproductoService.actualizarLote(contratodetalletipoproductoDTOLote);
    }

    /**
     * Elimina ContratoDetalleTipoProducto por Clave.
     * @param contratoDetalleId La clave de ContratoDetalle a eliminar.
     * @param tipoProductoId La clave de TipoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el ContratoDetalleTipoProducto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long contratoDetalleId, Long tipoProductoId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() contratodetalletipoproducto: {}, {}", contratoDetalleId, tipoProductoId);
        contratodetalletipoproductoService.eliminar(contratoDetalleId, tipoProductoId);
    }

    /**
     * Elimina Lote ContratoDetalleTipoProducto por Clave.
     * @param contratodetalletipoproductoDTOLote lista de claves de ContratoDetalleTipoProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  ContratoDetalleTipoProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        contratodetalletipoproductoService.eliminarLote(contratodetalletipoproductoDTOLote);
    }

    /**
     * Encuentra un ContratoDetalleTipoProducto por Clave.
     * @param contratoDetalleId La clave de ContratoDetalle a encontrar.
     * @param tipoProductoId La clave de TipoProducto a encontrar.
     * @return el ContratoDetalleTipoProducto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalleTipoProducto no es encontrado.
     */
    public ContratoDetalleTipoProductoDTO encontrarPorClave(Long contratoDetalleId, Long tipoProductoId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}, {}", contratoDetalleId, tipoProductoId);
        return contratodetalletipoproductoService.encontrarPorClave(contratoDetalleId, tipoProductoId);
    }

    /**
     * Obtiene todos los ContratoDetalleTipoProductos.
     * @return lista de todos ContratoDetalleTipoProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDetalleTipoProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return contratodetalletipoproductoService.obtenerTodos();
    }
}