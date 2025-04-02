package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ContratoDetalleDTO;
import com.elitsoft.servicampo.domain.entity.ContratoDetalle;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ContratoDetalleMapper;
import com.elitsoft.servicampo.mapstruct.ContratoDetalleMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ContratoDetalleError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad ContratoDetalle.
 */
@Service
public class ContratoDetalleService {

    @Autowired
    private ContratoDetalleMapper contratoDetalleMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoDetalleMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoDetalleService.class); //Logback


    /**
     * Agrega un nuevo ContratoDetalle.
     * @param contratoDetalleDTO el ContratoDetalle DTO.
     * @return el ContratoDetalle DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ContratoDetalle tiene errores.
     * @throws RecursoDuplicadoException si el recurso ContratoDetalle ya existe.
     */
    public ContratoDetalleDTO agregar(ContratoDetalleDTO contratoDetalleDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() ContratoDetalle");

        //  Valida Entrada
        if (contratoDetalleDTO == null) {
            logeador.error(Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           ContratoDetalleError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ContratoDetalle contratoDetalle = mapper.toEntity(contratoDetalleDTO);
            contratoDetalle = contratoDetalleMapper.agregar(contratoDetalle);
            ContratoDetalleDTO contratoDetalleDTOEncontrado = this.encontrarPorClave(contratoDetalle.getId());
            logeador.info("ContratoDetalle agregado exitosamente id: {}", contratoDetalle.getId());
            return contratoDetalleDTOEncontrado;
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTRATODETALLE_DUPLICADO_MENSAGE + ": {}, codigoError:{}", contratoDetalleDTO.getId(),
                           ContratoDetalleError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ContratoDetalleError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTRATODETALLE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLE_AGREGAR_MENSAJE + ": {}, codigoError:{}", contratoDetalleDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Actualiza un ContratoDetalle existente.
     * @param id la clave de ContratoDetalle a actualizar.
     * @param contratoDetalleDTO el ContratoDetalle DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalle no es encontrado.
     * @throws EntradaInvalidadException si la entrada ContratoDetalle tiene errores.
     */
    public void actualizar(Long id, ContratoDetalleDTO contratoDetalleDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() contratodetalle");

        //  Valida Entrada
        if (id == null || contratoDetalleDTO == null || contratoDetalleDTO.getId() == null) {
            logeador.error(Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((contratoDetalleDTO != null) ? contratoDetalleDTO.toString() : null  ),
                           ContratoDetalleError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(contratoDetalleDTO.getId())) {
            logeador.error(Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  contratoDetalleDTO.toString(),
                           ContratoDetalleError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleError.ID_INVALIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ContratoDetalleDTO contratoDetalleDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            ContratoDetalle contratoDetalle = mapper.toEntity(contratoDetalleDTO);
            contratoDetalle.setId(id);
            int registrosActualizados = contratoDetalleMapper.actualizar(contratoDetalle);
            logeador.info("contratodetalle actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLE_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, contratoDetalleDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLE_ACTUALIZAR_MENSAJE, e);
        }
    }


    /**
     * Elimina ContratoDetalle por Clave.
     * @param id la clave de ContratoDetalle a eliminar.
     * @throws RecursoNoEncontradoException si el ContratoDetalle no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() contratodetalle: {}", id);


        try {
            ContratoDetalleDTO contratoDetalleDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = contratoDetalleMapper.eliminar(id);
            logeador.info("contratodetalle eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLE_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote ContratoDetalle por Clave.
     * @param contratoDetalleDTOLote lista de claves de ContratoDetalle a eliminar.
     * @throws EntradaInvalidadException si la lista  ContratoDetalle esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDetalleDTO> contratoDetalleDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (contratoDetalleDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           ContratoDetalleError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoDetalleError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATODETALLE_ENTRADA_INVALIDA_MENSAGE);
        }
        

        try {
            int registrosEliminados = contratoDetalleMapper.eliminarLote(mapper.toEntityList(contratoDetalleDTOLote));
            logeador.info("Lote contratodetalle eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATODETALLE_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un ContratoDetalle por Clave.
     * @param id la clave ContratoDetalle a encontrar.
     * @return el ContratoDetalle DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ContratoDetalle no es encontrado.
     */
    public ContratoDetalleDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ContratoDetalleDTO contratoDetalleDTO = mapper.toDTO(contratoDetalleMapper.encontrarPorClave(id));

            if (contratoDetalleDTO != null) {
                logeador.info("contratodetalle encontrado por clave : {}", id);
            } else {
                logeador.info("contratodetalle clave:{} no encontrado codigoError:{}", id,
                              ContratoDetalleError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ContratoDetalleError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.CONTRATODETALLE_NO_ENCONTRADO_MENSAGE);
            }

            return contratoDetalleDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATODETALLE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los ContratoDetalles.
     * @return una lista de todos ContratoDetalle DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDetalleDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ContratoDetalleDTO> contratodetalleLista = mapper.toDTOList(contratoDetalleMapper.obtenerTodos());
            logeador.info("contratodetalles obtenidos");
            return contratodetalleLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATODETALLE_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATODETALLE_OBTENER_TODOS_MENSAJE, e);
        }
    }
}