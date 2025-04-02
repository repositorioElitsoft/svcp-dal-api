package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoComponenteDTO;
import com.elitsoft.servicampo.domain.entity.TipoComponente;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.TipoComponenteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TipoComponenteError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad TipoComponente.
 */
@Service
public class TipoComponenteService {

    @Autowired
    private TipoComponenteMapper tipocomponenteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoComponenteService.class); //Logback

    /**
     * Agrega un nuevo TipoComponente.
     * @param tipocomponenteDTO el TipoComponente DTO.
     * @return el TipoComponente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoComponente ya existe.
     */
    public TipoComponenteDTO agregar(TipoComponenteDTO tipocomponenteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() TipoComponente");

        //  Valida Entrada
        if (tipocomponenteDTO == null) {
            logeador.error(Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           TipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoComponente tipocomponente = mapper.toEntity(tipocomponenteDTO);
            tipocomponente = tipocomponenteMapper.agregar(tipocomponente);
            logeador.info("TipoComponente agregado exitosamente id: {}", tipocomponente.getId());
            return mapper.toDTO(tipocomponente);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_DUPLICADO_MENSAGE + ": {}, codigoError:{}", tipocomponenteDTO.getId(),
                           TipoComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(TipoComponenteError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_AGREGAR_MENSAJE + ": {}, codigoError:{}", tipocomponenteDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOCOMPONENTE_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos TipoComponente.
     * @param tipocomponenteDTOLote lista de TipoComponente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipocomponente ya existe.
     */
    public void agregarLote(List<TipoComponenteDTO> tipocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipocomponente");

        //  Valida Entrada
        if (tipocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          TipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoComponente> tipocomponenteLote = mapper.toEntityList(tipocomponenteDTOLote);

            int registrosAgregados =  tipocomponenteMapper.agregarLote(tipocomponenteLote);
            logeador.info("Lote TipoComponente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_DUPLICADO_MENSAGE + " codigoError:{}",
                          TipoComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(TipoComponenteError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPOCOMPONENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoComponente existente.
     * @param id la clave de TipoComponente a actualizar.
     * @param tipocomponenteDTO el TipoComponente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoComponente no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoComponente tiene errores.
     */
    public void actualizar(Long id, TipoComponenteDTO tipocomponenteDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tipocomponente");

        //  Valida Entrada
        if (id == null || tipocomponenteDTO == null || tipocomponenteDTO.getId() == null) {
            logeador.error(Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((tipocomponenteDTO != null) ? tipocomponenteDTO.toString() : null  ),
                           TipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(tipocomponenteDTO.getId())) {
            logeador.error(Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  tipocomponenteDTO.toString(),
                           TipoComponenteError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoComponenteError.ID_INVALIDO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoComponenteDTO tipocomponenteDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            TipoComponente tipocomponente = mapper.toEntity(tipocomponenteDTO);
            tipocomponente.setId(id);
            int registrosActualizados = tipocomponenteMapper.actualizar(tipocomponente);
            logeador.info("tipocomponente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, tipocomponenteDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOCOMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoComponente existentes.
     * @param tipocomponenteDTOLote lista de TipoComponente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoComponente tiene errores.
     */
    public void actualizarLote(List<TipoComponenteDTO> tipocomponenteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipocomponente");

        //  Valida Entrada
        if (tipocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           TipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoComponente> tipocomponenteLote = mapper.toEntityList(tipocomponenteDTOLote);
            int registrosActualizados = tipocomponenteMapper.actualizarLote(tipocomponenteLote);
            logeador.info("Lote tipocomponente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOCOMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoComponente por Clave.
     * @param id la clave de TipoComponente a eliminar.
     * @throws RecursoNoEncontradoException si el TipoComponente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipocomponente: {}", id);

        try {
            TipoComponenteDTO tipocomponenteDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipocomponenteMapper.eliminar(id);
            logeador.info("tipocomponente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOCOMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoComponente por Clave.
     * @param tipocomponenteDTOLote lista de claves de TipoComponente a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoComponente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<TipoComponenteDTO> tipocomponenteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (tipocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           TipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            int registrosEliminados = tipocomponenteMapper.eliminarLote(mapper.toEntityList(tipocomponenteDTOLote));
            logeador.info("Lote tipocomponente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPOCOMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoComponente por Clave.
     * @param id la clave TipoComponente a encontrar.
     * @return el TipoComponente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoComponente no es encontrado.
     */
    public TipoComponenteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoComponenteDTO tipocomponenteDTO = mapper.toDTO(tipocomponenteMapper.encontrarPorClave(id));

            if (tipocomponenteDTO != null) {
                logeador.info("tipocomponente encontrado por clave : {}", id);
            } else {
                logeador.info("tipocomponente clave:{} no encontrado codigoError:{}", id,
                              TipoComponenteError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(TipoComponenteError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.TIPOCOMPONENTE_NO_ENCONTRADO_MENSAGE);
            }

            return tipocomponenteDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOCOMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoComponentes.
     * @return una lista de todos TipoComponente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoComponenteDTO> tipocomponenteLista = mapper.toDTOList(tipocomponenteMapper.obtenerTodos());
            logeador.info("tipocomponentes obtenidos");
            return tipocomponenteLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOCOMPONENTE_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPOCOMPONENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }

}