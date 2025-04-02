package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleProductoDTO;
import com.elitsoft.servicampo.domain.entity.ContratoDetalleProducto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ContratoDetalleProductoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleProductoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ContratoDetalleProductoError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad ContratoDetalleProducto.
 */
@Service
public class ContratoDetalleProductoService {

    @Autowired
    private ContratoDetalleProductoMapper contratodetalleproductoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    @Autowired
    private ContratoDetalleService contratoDetalleService;

    @Autowired
    private TipoProductoService tipoProductoService;

    @Autowired
    private ProductoService productoService;

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleProductoService.class); //Logback


    /**
     * Agrega un nuevo ContratoDetalleProducto.
     * @param contratodetalleproductoDTO el ContratoDetalleProducto DTO.
     * @return el ContratoDetalleProducto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso ContratoDetalleProducto ya existe.
     */
    public ContratoDetalleProductoDTO agregar(ContratoDetalleProductoDTO contratodetalleproductoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() ContratoDetalleProducto");

        //  Valida Entrada
        if (contratodetalleproductoDTO == null) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           ContratoDetalleProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        contratoDetalleService.encontrarPorClave(contratodetalleproductoDTO.getContratoDetalle().getId());
        tipoProductoService.encontrarPorClave(contratodetalleproductoDTO.getTipoProducto().getId());
        productoService.encontrarPorClave(contratodetalleproductoDTO.getProducto().getId());

        try {
            ContratoDetalleProducto contratodetalleproducto = mapper.toEntity(contratodetalleproductoDTO);
            contratodetalleproducto = contratodetalleproductoMapper.agregar(contratodetalleproducto);
            ContratoDetalleProductoDTO contratodetalleproductoDTOEncontrado = this.encontrarPorClave(contratodetalleproductoDTO.getContratoDetalle().getId(),
                                                                                 contratodetalleproductoDTO.getTipoProducto().getId(),
                                                                                 contratodetalleproducto.getCorrelativo()); // Verifica si existe el recurso

            logeador.info("ContratoDetalleProducto agregado exitosamente id: ContratoDetalleId={}, TipoProductoId={}, Correlativo={}, ProductoId={} ", contratodetalleproductoDTOEncontrado.getContratoDetalle().getId(),
                    contratodetalleproductoDTOEncontrado.getTipoProducto().getId(),  contratodetalleproducto.getCorrelativo(), contratodetalleproductoDTOEncontrado.getProducto().getId());
            return contratodetalleproductoDTOEncontrado;
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_DUPLICADO_MENSAGE + ": {}, {}, {}, {} codigoError:{}", contratodetalleproductoDTO.getContratoDetalle().getId(),
                    contratodetalleproductoDTO.getTipoProducto().getId(), contratodetalleproductoDTO.getCorrelativo(), contratodetalleproductoDTO.getProducto().getId(),
                           ContratoDetalleProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ContratoDetalleProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_AGREGAR_MENSAJE + ": {}, codigoError:{}", contratodetalleproductoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLEPRODUCTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos ContratoDetalleProducto.
     * @param contratodetalleproductoDTOLote lista de ContratoDetalleProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso contratodetalleproducto ya existe.
     */
    public void agregarLote(List<ContratoDetalleProductoDTO> contratodetalleproductoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() contratodetalleproducto");

        //  Valida Entrada
        if (contratodetalleproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          ContratoDetalleProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<ContratoDetalleProducto> contratodetalleproductoLote = mapper.toEntityList(contratodetalleproductoDTOLote);

            int registrosAgregados =  contratodetalleproductoMapper.agregarLote(contratodetalleproductoLote);
            logeador.info("Lote ContratoDetalleProducto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_DUPLICADO_MENSAGE + " codigoError:{}",
                          ContratoDetalleProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ContratoDetalleProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLEPRODUCTO_AGREGAR_LOTE_MENSAJE, e);
        }
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
    public void actualizar(Long contratoDetalleId, Long tipoProductoId, Long correlativoId, ContratoDetalleProductoDTO contratodetalleproductoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() contratodetalleproducto");

        //  Valida Entrada
        if (contratoDetalleId == null || tipoProductoId ==  null || correlativoId == null || contratodetalleproductoDTO == null
                || contratodetalleproductoDTO.getContratoDetalle() == null || contratodetalleproductoDTO.getContratoDetalle().getId() == null
                || contratodetalleproductoDTO.getTipoProducto() == null || contratodetalleproductoDTO.getTipoProducto().getId() == null
                || contratodetalleproductoDTO.getCorrelativo() == null || contratodetalleproductoDTO.getProducto() == null
                || contratodetalleproductoDTO.getProducto().getId() == null) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((contratodetalleproductoDTO != null) ? contratodetalleproductoDTO.toString() : null  ),
                           ContratoDetalleProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!contratoDetalleId.equals(contratodetalleproductoDTO.getContratoDetalle().getId())) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  contratodetalleproductoDTO.toString(),
                           ContratoDetalleProductoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.ID_INVALIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!tipoProductoId.equals(contratodetalleproductoDTO.getTipoProducto().getId())) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  contratodetalleproductoDTO.toString(),
                    ContratoDetalleProductoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.ID_INVALIDO.getCodigoError(),
                    Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!correlativoId.equals(contratodetalleproductoDTO.getCorrelativo())) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  contratodetalleproductoDTO.toString(),
                    ContratoDetalleProductoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.ID_INVALIDO.getCodigoError(),
                    Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ContratoDetalleProductoDTO contratodetalleproductoDTOEncontrado = this.encontrarPorClave(contratoDetalleId, tipoProductoId, correlativoId); // Verifica si existe el recurso
            ContratoDetalleProducto contratodetalleproducto = mapper.toEntity(contratodetalleproductoDTO);
            int registrosActualizados = contratodetalleproductoMapper.actualizar(contratodetalleproducto);
            logeador.info("contratodetalleproducto actualizado exitosamente: {},{},{} registros actualizados: {}", contratoDetalleId, tipoProductoId, correlativoId, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ACTUALIZAR_MENSAJE + ": {},{},{} codigoError:{}", contratoDetalleId, tipoProductoId, correlativoId, contratodetalleproductoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLEPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de ContratoDetalleProducto existentes.
     * @param contratodetalleproductoDTOLote lista de ContratoDetalleProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleProducto tiene errores.
     */
    public void actualizarLote(List<ContratoDetalleProductoDTO> contratodetalleproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() contratodetalleproducto");

        //  Valida Entrada
        if (contratodetalleproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           ContratoDetalleProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<ContratoDetalleProducto> contratodetalleproductoLote = mapper.toEntityList(contratodetalleproductoDTOLote);
            int registrosActualizados = contratodetalleproductoMapper.actualizarLote(contratodetalleproductoLote);
            logeador.info("Lote contratodetalleproducto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLEPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
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
        logeador.debug("eliminar() contratodetalleproducto: {}", contratoDetalleId);



        try {
            ContratoDetalleProductoDTO contratodetalleproductoDTO = this.encontrarPorClave(contratoDetalleId, tipoProductoId, correlativoId); // Verifica si existe
            int registrosEliminados = contratodetalleproductoMapper.eliminar(contratoDetalleId, tipoProductoId, correlativoId );
            logeador.info("contratodetalleproducto eliminado: {}, registros eliminados: {}", contratoDetalleId, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", contratoDetalleId,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLEPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote ContratoDetalleProducto por Clave.
     * @param contratodetalleproductoDTOLote lista de claves de ContratoDetalleProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  ContratoDetalleProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDetalleProductoDTO> contratodetalleproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (contratodetalleproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           ContratoDetalleProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLEPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = contratodetalleproductoMapper.eliminarLote(mapper.toEntityList(contratodetalleproductoDTOLote));
            logeador.info("Lote contratodetalleproducto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLEPRODUCTO_ELIMINAR_MENSAJE, e);
        }
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
        logeador.debug("obtenerPorClave(): {}, {}, {}", contratoDetalleId, tipoProductoId, correlativoId );

        try {
            ContratoDetalleProductoDTO contratodetalleproductoDTO = mapper.toDTO(contratodetalleproductoMapper.encontrarPorClave(contratoDetalleId, tipoProductoId, correlativoId));

            if (contratodetalleproductoDTO != null) {
                logeador.info("contratodetalleproducto encontrado por clave : {}, {}, {}", contratoDetalleId, tipoProductoId, correlativoId);
            } else {
                logeador.info("contratodetalleproducto clave:{}, {}, {} no encontrado codigoError:{}", contratoDetalleId, tipoProductoId, correlativoId,
                              ContratoDetalleProductoError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ContratoDetalleProductoError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.CONTRATODETALLEPRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            return contratodetalleproductoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, {}, {}, codigoError:{}", contratoDetalleId, tipoProductoId, correlativoId,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLEPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los ContratoDetalleProductos.
     * @return una lista de todos ContratoDetalleProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDetalleProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ContratoDetalleProductoDTO> contratodetalleproductoLista = mapper.toDTOList(contratodetalleproductoMapper.obtenerTodos());
            logeador.info("contratodetalleproductos obtenidos");
            return contratodetalleproductoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLEPRODUCTO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLEPRODUCTO_OBTENER_TODOS_MENSAJE, e);
        }
    }
}