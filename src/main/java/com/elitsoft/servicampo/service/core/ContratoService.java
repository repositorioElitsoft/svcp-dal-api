package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ContratoDTO;
import com.elitsoft.servicampo.domain.entity.Contrato;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ContratoMapper;
import com.elitsoft.servicampo.mapstruct.ContratoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ContratoError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Contrato.
 */
@Service
public class ContratoService {

    @Autowired
    private ContratoMapper contratoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContratoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContratoService.class); //Logback


    /**
     * Agrega un nuevo Contrato.
     * @param contratoDTO el Contrato DTO.
     * @return el Contrato DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contrato tiene errores.
     * @throws RecursoDuplicadoException si el recurso Contrato ya existe.
     */
    public ContratoDTO agregar(ContratoDTO contratoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() Contrato");

        //  Valida Entrada
        if (contratoDTO == null) {
            logeador.error(Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           ContratoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Contrato contrato = mapper.toEntity(contratoDTO);
            contrato = contratoMapper.agregar(contrato);
            ContratoDTO contratoDTOEncontrado = this.encontrarPorClave(contrato.getId());
            logeador.info("Contrato agregado exitosamente id: {}", contrato.getId());
            return contratoDTOEncontrado;
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTRATO_DUPLICADO_MENSAGE + ": {}, codigoError:{}", contratoDTO.getId(),
                           ContratoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ContratoError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTRATO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATO_AGREGAR_MENSAJE + ": {}, codigoError:{}", contratoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATO_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Actualiza un Contrato existente.
     * @param id la clave de Contrato a actualizar.
     * @param contratoDTO el Contrato DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contrato no es encontrado.
     * @throws EntradaInvalidadException si la entrada Contrato tiene errores.
     */
    public void actualizar(Long id, ContratoDTO contratoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() contrato");

        //  Valida Entrada
        if (id == null || contratoDTO == null || contratoDTO.getId() == null) {
            logeador.error(Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((contratoDTO != null) ? contratoDTO.toString() : null  ),
                           ContratoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(contratoDTO.getId())) {
            logeador.error(Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  contratoDTO.toString(),
                           ContratoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoError.ID_INVALIDO.getCodigoError(),
                                                Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ContratoDTO contratoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Contrato contrato = mapper.toEntity(contratoDTO);
            contrato.setId(id);
            int registrosActualizados = contratoMapper.actualizar(contrato);
            logeador.info("contrato actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATO_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, contratoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATO_ACTUALIZAR_MENSAJE, e);
        }
    }


    /**
     * Elimina Contrato por Clave.
     * @param id la clave de Contrato a eliminar.
     * @throws RecursoNoEncontradoException si el Contrato no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() contrato: {}", id);


        try {
            ContratoDTO contratoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = contratoMapper.eliminar(id);
            logeador.info("contrato eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Contrato por Clave.
     * @param contratoDTOLote lista de claves de Contrato a eliminar.
     * @throws EntradaInvalidadException si la lista  Contrato esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ContratoDTO> contratoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (contratoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           ContratoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ContratoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTRATO_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = contratoMapper.eliminarLote(mapper.toEntityList(contratoDTOLote));
            logeador.info("Lote contrato eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTRATO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Contrato por Clave.
     * @param id la clave Contrato a encontrar.
     * @return el Contrato DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contrato no es encontrado.
     */
    public ContratoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ContratoDTO contratoDTO = mapper.toDTO(contratoMapper.encontrarPorClave(id));

            if (contratoDTO != null) {
                logeador.info("contrato encontrado por clave : {}", id);
            } else {
                logeador.info("contrato clave:{} no encontrado codigoError:{}", id,
                              ContratoError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ContratoError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.CONTRATO_NO_ENCONTRADO_MENSAGE);
            }

            return contratoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTRATO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Contratos.
     * @return una lista de todos Contrato DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContratoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ContratoDTO> contratoLista = mapper.toDTOList(contratoMapper.obtenerTodos());
            logeador.info("contratos obtenidos");
            return contratoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTRATO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTRATO_OBTENER_TODOS_MENSAJE, e);
        }
    }

}