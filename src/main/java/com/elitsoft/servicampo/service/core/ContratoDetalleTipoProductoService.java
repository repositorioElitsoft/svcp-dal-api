package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleTipoProductoDTO;
import com.elitsoft.servicampo.domain.entity.ContratoDetalleTipoProducto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ContratoDetalleTipoProductoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleTipoProductoMapStruct;
import com.elitsoft.servicampo.service.error.ContratoDetalleError;
import com.elitsoft.servicampo.service.error.TipoProductoError;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ContratoDetalleTipoProductoError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad ContratoDetalleTipoProducto.
 */
@Service
public class ContratoDetalleTipoProductoService {

    @Autowired
    private ContratoDetalleTipoProductoMapper contratodetalletipoproductoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleTipoProductoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    @Autowired
    private ContratoDetalleService contratoDetalleService;

    @Autowired
    private TipoProductoService tipoProductoService;

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleTipoProductoService.class); //Logback

    /**
     * Agrega un nuevo ContratoDetalleTipoProducto.
     * @param contratodetalletipoproductoDTO el ContratoDetalleTipoProducto DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleTipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso contratodetalletipoproducto ya existe.
     */
    public void agregar(ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() contratodetalletipoproducto");

        //  Valida Entrada
        if (contratodetalletipoproductoDTO == null || contratodetalletipoproductoDTO.getContratoDetalle() == null || contratodetalletipoproductoDTO.getTipoProducto() == null ) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((contratodetalletipoproductoDTO != null) ? contratodetalletipoproductoDTO.toString() : null  ),
                           ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Valida la existencia
        contratoDetalleService.encontrarPorClave(contratodetalletipoproductoDTO.getContratoDetalle().getId());
        tipoProductoService.encontrarPorClave(contratodetalletipoproductoDTO.getTipoProducto().getId());


        try {
            ContratoDetalleTipoProducto contratodetalletipoproducto = mapper.toEntity(contratodetalletipoproductoDTO);
            Long nuevoId = contratodetalletipoproductoMapper.agregar(contratodetalletipoproducto);
            logeador.info("ContratoDetalleTipoProducto agregado exitosamente id: {}", nuevoId);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_DUPLICADO_MENSAGE + ": {}, {} codigoError:{}", contratodetalletipoproductoDTO.getContratoDetalle().getId(),
                           contratodetalletipoproductoDTO.getTipoProducto().getId(), ContratoDetalleTipoProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ContratoDetalleTipoProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_AGREGAR_MENSAJE + ": {}, {} codigoError:{}", contratodetalletipoproductoDTO.getContratoDetalle().getId(),
                           contratodetalletipoproductoDTO.getTipoProducto().getId(), GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLETIPOPRODUCTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos ContratoDetalleTipoProducto.
     * @param contratodetalletipoproductoDTOLote lista de ContratoDetalleTipoProducto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleTipoProducto tiene errores.
     * @throws RecursoDuplicadoException si el recurso contratodetalletipoproducto ya existe.
     */
    public void agregarLote(List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() contratodetalletipoproducto");

        //  Valida Entrada
        if (contratodetalletipoproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<ContratoDetalleTipoProducto> contratodetalletipoproductoLote = mapper.toEntityList(contratodetalletipoproductoDTOLote);

            int registrosAgregados =  contratodetalletipoproductoMapper.agregarLote(contratodetalletipoproductoLote);
            logeador.info("Lote ContratoDetalleTipoProducto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_DUPLICADO_MENSAGE + " codigoError:{}",
                          ContratoDetalleTipoProductoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ContratoDetalleTipoProductoError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLETIPOPRODUCTO_AGREGAR_LOTE_MENSAJE, e);
        }
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
    public void actualizar(Long contratoDetalleId, Long tipoProductoId, ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() contratodetalletipoproducto");

        //  Valida Entrada
        if (contratodetalletipoproductoDTO == null || contratodetalletipoproductoDTO.getContratoDetalle() == null || contratodetalletipoproductoDTO.getTipoProducto() == null ) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((contratodetalletipoproductoDTO != null) ? contratodetalletipoproductoDTO.toString() : null  ),
                           ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id Contrato Detalle
        if (!contratoDetalleId.equals(contratodetalletipoproductoDTO.getContratoDetalle().getId())) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  contratodetalletipoproductoDTO.toString(),
                    ContratoDetalleError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleError.ID_INVALIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id Tipo Producto
        if (!tipoProductoId.equals(contratodetalletipoproductoDTO.getTipoProducto().getId())) {
            logeador.error(Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  contratodetalletipoproductoDTO.toString(),
                    TipoProductoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoProductoError.ID_INVALIDO.getCodigoError(),
                    Constantes.TIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Valida la existencia
        contratoDetalleService.encontrarPorClave(contratodetalletipoproductoDTO.getContratoDetalle().getId());
        tipoProductoService.encontrarPorClave(contratodetalletipoproductoDTO.getTipoProducto().getId());

        try {
            ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTOEncontrado = this.encontrarPorClave(contratoDetalleId, tipoProductoId); // Verifica si existe el recurso
            ContratoDetalleTipoProducto contratodetalletipoproducto = mapper.toEntity(contratodetalletipoproductoDTO);
            int registrosActualizados = contratodetalletipoproductoMapper.actualizar(contratodetalletipoproducto);
            logeador.info("contratodetalletipoproducto actualizado exitosamente: {}, {} registros actualizados: {}", contratoDetalleId, tipoProductoId, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ACTUALIZAR_MENSAJE + ": contratoDetalleId={}, tipoProducto={}, {} codigoError:{}", contratoDetalleId, tipoProductoId, contratodetalletipoproductoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLETIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de ContratoDetalleTipoProducto existentes.
     * @param contratodetalletipoproductoDTOLote lista de ContratoDetalleTipoProducto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalleTipoProducto tiene errores.
     */
    public void actualizarLote(List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() contratodetalletipoproducto");

        //  Valida Entrada
        if (contratodetalletipoproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<ContratoDetalleTipoProducto> contratodetalletipoproductoLote = mapper.toEntityList(contratodetalletipoproductoDTOLote);
            int registrosActualizados = contratodetalletipoproductoMapper.actualizarLote(contratodetalletipoproductoLote);
            logeador.info("Lote contratodetalletipoproducto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLETIPOPRODUCTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina ContratoDetalleTipoProducto por Clave.
     * @param contratoDetalleId La clave de ContratoDetalle a eliminar.
     * @param tipoProductoId La clave de TipoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el ContratoDetalleTipoProducto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long contratoDetalleId, Long tipoProductoId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() contratodetalletipoproducto: {}, {}", contratoDetalleId, tipoProductoId);

        //Verifica integridad referencial
        //this.verificarIntegridadEliminar(id);

        //Valida la existencia
        contratoDetalleService.encontrarPorClave(contratoDetalleId);
        tipoProductoService.encontrarPorClave(tipoProductoId);

        try {
            ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO = this.encontrarPorClave(contratoDetalleId, tipoProductoId); // Verifica si existe
            int registrosEliminados = contratodetalletipoproductoMapper.eliminar(contratoDetalleId, tipoProductoId);
            logeador.info("contratodetalletipoproducto eliminado: {},{} registros eliminados: {}", contratoDetalleId, tipoProductoId, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ELIMINAR_MENSAJE + ": {}, {} codigoError:{}", contratoDetalleId, tipoProductoId,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLETIPOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote ContratoDetalleTipoProducto por Clave.
     * @param contratodetalletipoproductoDTOLote lista de claves de ContratoDetalleTipoProducto a eliminar.
     * @throws EntradaInvalidadException si la lista  ContratoDetalleTipoProducto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (contratodetalletipoproductoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleTipoProductoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLETIPOPRODUCTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
        //for (Long id : idLote) {
        //    this.verificarIntegridadEliminar(id);
        //}

        try {
            int registrosEliminados = contratodetalletipoproductoMapper.eliminarLote(mapper.toEntityList(contratodetalletipoproductoDTOLote));
            logeador.info("Lote contratodetalletipoproducto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLETIPOPRODUCTO_ELIMINAR_MENSAJE, e);
        }
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
        logeador.debug("obtenerPorClave(): {}, {}", contratoDetalleId, tipoProductoId);

        try {
            ContratoDetalleTipoProductoDTO contratodetalletipoproductoDTO = mapper.toDTO(contratodetalletipoproductoMapper.encontrarPorClave(contratoDetalleId, tipoProductoId));

            if (contratodetalletipoproductoDTO != null) {
                logeador.info("contratodetalletipoproducto encontrado por clave : {}, {}", contratoDetalleId, tipoProductoId);
            } else {
                logeador.info("contratodetalletipoproducto clave:{}, {} no encontrado codigoError:{}", contratoDetalleId, tipoProductoId,
                              ContratoDetalleTipoProductoError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ContratoDetalleTipoProductoError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.CONTRATODETALLETIPOPRODUCTO_NO_ENCONTRADO_MENSAGE);
            }

            return contratodetalletipoproductoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, {} codigoError:{}", contratoDetalleId, tipoProductoId,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLETIPOPRODUCTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los ContratoDetalleTipoProductos.
     * @return una lista de todos ContratoDetalleTipoProducto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDetalleTipoProductoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ContratoDetalleTipoProductoDTO> contratodetalletipoproductoLista = mapper.toDTOList(contratodetalletipoproductoMapper.obtenerTodos());
            logeador.info("contratodetalletipoproductos obtenidos");
            return contratodetalletipoproductoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLETIPOPRODUCTO_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de ContratoDetalleTipoProducto
     * @param id la clave ContratoDetalleTipoProducto a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
    /*
    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
        logeador.debug("verificarIntegridadEliminar() contratodetalletipoproducto: {}", id);

        boolean entityRelacionadoPorContratoDetalleTipoProducto = false;

        try {
            entityRelacionadoPorContratoDetalleTipoProducto = this.entityRelacionadoPorContratoDetalleTipoProducto(id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLETIPOPRODUCTO_ELIMINAR_MENSAJE, e);
        }

        //Verifca la integridad con sectores
        if (entityRelacionadoPorContratoDetalleTipoProducto) {
            throw new RecursoEliminarException(ContratoDetalleTipoProductoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                                               Constantes.CONTRATODETALLETIPOPRODUCTO_VIOLACION_INTEGRIDAD_MENSAGE);
        }

    }
    */

    /**
     * Buscar ContratoDetalleTipoProducto que tengan EntityRelacionado.
     * @param id la clave ContratoDetalleTipoProducto a encontrar.
     * @return boolean ContratoDetalleTipoProducto tiene o no registros asociados
     * @throws BaseDatosException
     */
    /*
    public boolean entityRelacionadoPorContratoDetalleTipoProducto(Long id) throws  BaseDatosException {
        logeador.debug("entityRelacionadoPorContratoDetalleTipoProducto() contratodetalletipoproducto: {}", id);

        try {
            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorContratoDetalleTipoProducto(id); // Verifica si tiene EntityRelacionado  asociados
            if (!entitys.isEmpty()) {
                logeador.info("contratodetalletipoproducto  tiene #EntityRelacionado# asociados");
                return true;
            } else{
                logeador.info("contratodetalletipoproducto no tiene #EntityRelacionado# asociados");
                return false;
            }
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLETIPOPRODUCTO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLETIPOPRODUCTO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }
    */
}