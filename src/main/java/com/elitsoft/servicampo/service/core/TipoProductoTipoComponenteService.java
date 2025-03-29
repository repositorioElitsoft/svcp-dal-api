package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoProductoTipoComponenteDTO;
import com.elitsoft.servicampo.domain.entity.TipoProductoTipoComponente;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoProductoTipoComponenteMapper;
import com.elitsoft.servicampo.mapstruct.TipoProductoTipoComponenteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.TipoProductoTipoComponenteError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad TipoProductoTipoComponente.
 */
@Service
public class TipoProductoTipoComponenteService {

    @Autowired
    private TipoProductoTipoComponenteMapper tipoproductotipocomponenteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoProductoTipoComponenteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoProductoTipoComponenteService.class); //Logback

    /**
     * Agrega un nuevo TipoProductoTipoComponente.
     * @param tipoproductotipocomponenteDTO el TipoProductoTipoComponente DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipoproductotipocomponente ya existe.
     */
    public void agregar(TipoProductoTipoComponenteDTO tipoproductotipocomponenteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipoproductotipocomponente");

        //  Valida Entrada
        if (tipoproductotipocomponenteDTO == null || tipoproductotipocomponenteDTO.getTipoComponente()  == null || tipoproductotipocomponenteDTO.getTipoComponente().getId() == null
                || tipoproductotipocomponenteDTO.getTipoProducto() == null || tipoproductotipocomponenteDTO.getTipoProducto().getId() ==  null ) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((tipoproductotipocomponenteDTO != null) ? tipoproductotipocomponenteDTO.toString() : null  ),
                           TipoProductoTipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoProductoTipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoProductoTipoComponente tipoproductotipocomponente = mapper.toEntity(tipoproductotipocomponenteDTO);
            Long nuevoId = tipoproductotipocomponenteMapper.agregar(tipoproductotipocomponente);
            logeador.info("TipoProductoTipoComponente agregado exitosamente id: {}", nuevoId);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_DUPLICADO_MENSAGE + ": {}, {} codigoError:{}", tipoproductotipocomponenteDTO.getTipoComponente().getId() ,
                           tipoproductotipocomponenteDTO.getTipoProducto().getId() , TipoProductoTipoComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(TipoProductoTipoComponenteError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_AGREGAR_MENSAJE + ": {}, codigoError:{}", tipoproductotipocomponenteDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOPRODUCTOTIPOCOMPONENTE_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Agrega Lote nuevos TipoProductoTipoComponente.
     * @param tipoproductotipocomponenteDTOLote lista de TipoProductoTipoComponente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     * @throws RecursoDuplicadoException si el recurso tipoproductotipocomponente ya existe.
     */
    public void agregarLote(List<TipoProductoTipoComponenteDTO> tipoproductotipocomponenteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoproductotipocomponente");

        //  Valida Entrada
        if (tipoproductotipocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          TipoProductoTipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoProductoTipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<TipoProductoTipoComponente> tipoproductotipocomponenteLote = mapper.toEntityList(tipoproductotipocomponenteDTOLote);

            int registrosAgregados =  tipoproductotipocomponenteMapper.agregarLote(tipoproductotipocomponenteLote);
            logeador.info("Lote TipoProductoTipoComponente agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_DUPLICADO_MENSAGE + " codigoError:{}",
                          TipoProductoTipoComponenteError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(TipoProductoTipoComponenteError.DUPLICADO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPOPRODUCTOTIPOCOMPONENTE_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un TipoProductoTipoComponente existente.
     * @param tipoComponenteId La clave de TipoComponente a actualizar.
     * @param tipoProductoId La clave de TipoProducto a actualizar.
     * @param tipoproductotipocomponenteDTO el TipoProductoTipoComponente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProductoTipoComponente no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     */
    public void actualizar(Long tipoComponenteId, Long tipoProductoId, TipoProductoTipoComponenteDTO tipoproductotipocomponenteDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() tipoproductotipocomponente");

        //  Valida Entrada
        if (tipoComponenteId == null || tipoProductoId ==null || tipoproductotipocomponenteDTO == null ||  tipoproductotipocomponenteDTO.getTipoComponente()  == null
                || tipoproductotipocomponenteDTO.getTipoComponente().getId() == null || tipoproductotipocomponenteDTO.getTipoProducto() == null
                || tipoproductotipocomponenteDTO.getTipoProducto().getId() ==  null) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((tipoproductotipocomponenteDTO != null) ? tipoproductotipocomponenteDTO.toString() : null  ),
                           TipoProductoTipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoProductoTipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!tipoComponenteId.equals(tipoproductotipocomponenteDTO.getTipoComponente().getId()) || !tipoProductoId.equals(tipoproductotipocomponenteDTO.getTipoProducto().getId())) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  tipoproductotipocomponenteDTO.toString(),
                           TipoProductoTipoComponenteError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoProductoTipoComponenteError.ID_INVALIDO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            TipoProductoTipoComponenteDTO tipoproductotipocomponenteDTOEncontrado = this.encontrarPorClave(tipoComponenteId,tipoProductoId); // Verifica si existe el recurso
            TipoProductoTipoComponente tipoproductotipocomponente = mapper.toEntity(tipoproductotipocomponenteDTO);
            int registrosActualizados = tipoproductotipocomponenteMapper.actualizar(tipoComponenteId, tipoProductoId, tipoproductotipocomponente);
            logeador.info("tipoproductotipocomponente actualizado exitosamente: {}, {} registros actualizados: {}", tipoComponenteId, tipoProductoId, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ACTUALIZAR_MENSAJE + ": tipoComponenteId={}, tipoProductoId={}, {} codigoError:{}", tipoComponenteId,
                            tipoProductoId, tipoproductotipocomponenteDTO.toString(), GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de TipoProductoTipoComponente existentes.
     * @param tipoproductotipocomponenteDTOLote lista de TipoProductoTipoComponente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoProductoTipoComponente tiene errores.
     */
    public void actualizarLote(List<TipoProductoTipoComponenteDTO> tipoproductotipocomponenteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoproductotipocomponente");

        //  Valida Entrada
        if (tipoproductotipocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           TipoProductoTipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoProductoTipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<TipoProductoTipoComponente> tipoproductotipocomponenteLote = mapper.toEntityList(tipoproductotipocomponenteDTOLote);
            int registrosActualizados = tipoproductotipocomponenteMapper.actualizarLote(tipoproductotipocomponenteLote);
            logeador.info("Lote tipoproductotipocomponente actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina TipoProductoTipoComponente por Clave.
     * @param tipoComponenteId La clave de TipoComponente a eliminar.
     * @param tipoProductoId La clave de TipoProducto a eliminar.
     * @throws RecursoNoEncontradoException si el TipoProductoTipoComponente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long tipoComponenteId, Long tipoProductoId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoproductotipocomponente: {}, {}", tipoComponenteId, tipoProductoId);

        //Verifica integridad referencial
        //this.verificarIntegridadEliminar(id);

        try {
            TipoProductoTipoComponenteDTO tipoproductotipocomponenteDTO = this.encontrarPorClave(tipoComponenteId, tipoProductoId); // Verifica si existe
            int registrosEliminados = tipoproductotipocomponenteMapper.eliminar(tipoComponenteId,tipoProductoId );
            logeador.info("tipoproductotipocomponente eliminado: {}, {}, registros eliminados: {}", tipoComponenteId,tipoProductoId, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ELIMINAR_MENSAJE + ": {}, {}, codigoError:{}", tipoComponenteId, tipoProductoId,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote TipoProductoTipoComponente por Clave.
     * @param tipoproductotipocomponenteDTOLote lista de claves de TipoProductoTipoComponente a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoProductoTipoComponente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<TipoProductoTipoComponenteDTO> tipoproductotipocomponenteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (tipoproductotipocomponenteDTOLote.isEmpty()) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           TipoProductoTipoComponenteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(TipoProductoTipoComponenteError.REQUERIDO.getCodigoError(),
                                                Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
        //for (Long id : idLote) {
        //    this.verificarIntegridadEliminar(id);
        //}

        try {
            int registrosEliminados = tipoproductotipocomponenteMapper.eliminarLote(mapper.toEntityList(tipoproductotipocomponenteDTOLote));
            logeador.info("Lote tipoproductotipocomponente eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un TipoProductoTipoComponente por Clave.
     * @param tipoComponenteId La clave de TipoComponente a encontrar.
     * @param tipoProductoId La clave de TipoProducto a encontrar.
     * @return el TipoProductoTipoComponente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoProductoTipoComponente no es encontrado.
     */
    public TipoProductoTipoComponenteDTO encontrarPorClave(Long tipoComponenteId, Long tipoProductoId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}, {}", tipoComponenteId,tipoProductoId );

        try {
            TipoProductoTipoComponenteDTO tipoproductotipocomponenteDTO = mapper.toDTO(tipoproductotipocomponenteMapper.encontrarPorClave(tipoComponenteId, tipoProductoId));

            if (tipoproductotipocomponenteDTO != null) {
                logeador.info("tipoproductotipocomponente encontrado por clave : {}, {}", tipoComponenteId, tipoProductoId );
            } else {
                logeador.info("tipoproductotipocomponente clave:{}, {} no encontrado codigoError:{}", tipoComponenteId,tipoProductoId,
                              TipoProductoTipoComponenteError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(TipoProductoTipoComponenteError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.TIPOPRODUCTOTIPOCOMPONENTE_NO_ENCONTRADO_MENSAGE);
            }

            return tipoproductotipocomponenteDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, {} codigoError:{}", tipoComponenteId, tipoProductoId,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los TipoProductoTipoComponentes.
     * @return una lista de todos TipoProductoTipoComponente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoProductoTipoComponenteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoProductoTipoComponenteDTO> tipoproductotipocomponenteLista = mapper.toDTOList(tipoproductotipocomponenteMapper.obtenerTodos());
            logeador.info("tipoproductotipocomponentes obtenidos");
            return tipoproductotipocomponenteLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.TIPOPRODUCTOTIPOCOMPONENTE_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de TipoProductoTipoComponente
     * @param id la clave TipoProductoTipoComponente a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
    /*
    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
        logeador.debug("verificarIntegridadEliminar() tipoproductotipocomponente: {}", id);

        boolean entityRelacionadoPorTipoProductoTipoComponente = false;

        try {
            entityRelacionadoPorTipoProductoTipoComponente = this.entityRelacionadoPorTipoProductoTipoComponente(id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOPRODUCTOTIPOCOMPONENTE_ELIMINAR_MENSAJE, e);
        }

        //Verifca la integridad con sectores
        if (entityRelacionadoPorTipoProductoTipoComponente) {
            throw new RecursoEliminarException(TipoProductoTipoComponenteError.INTEGRIDAD_VIOLADA.getCodigoError(),
                                               Constantes.TIPOPRODUCTOTIPOCOMPONENTE_VIOLACION_INTEGRIDAD_MENSAGE);
        }

    }
    */

    /**
     * Buscar TipoProductoTipoComponente que tengan EntityRelacionado.
     * @param id la clave TipoProductoTipoComponente a encontrar.
     * @return boolean TipoProductoTipoComponente tiene o no registros asociados
     * @throws BaseDatosException
     */
    /*
    public boolean entityRelacionadoPorTipoProductoTipoComponente(Long id) throws  BaseDatosException {
        logeador.debug("entityRelacionadoPorTipoProductoTipoComponente() tipoproductotipocomponente: {}", id);

        try {
            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorTipoProductoTipoComponente(id); // Verifica si tiene EntityRelacionado  asociados
            if (!entitys.isEmpty()) {
                logeador.info("tipoproductotipocomponente  tiene #EntityRelacionado# asociados");
                return true;
            } else{
                logeador.info("tipoproductotipocomponente no tiene #EntityRelacionado# asociados");
                return false;
            }
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOPRODUCTOTIPOCOMPONENTE_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.TIPOPRODUCTOTIPOCOMPONENTE_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }
    */
}