package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.DireccionDTO;
import com.elitsoft.servicampo.domain.entity.Direccion;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.DireccionMapper;
import com.elitsoft.servicampo.mapstruct.DireccionMapStruct;
import com.elitsoft.servicampo.service.error.ClienteError;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.DireccionError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Direccion.
 */
@Service
public class DireccionService {

    @Autowired
    private DireccionMapper direccionMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DireccionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(DireccionService.class); //Logback


    /**
     * Agrega un nuevo Direccion.
     * @param direccionDTO el Direccion DTO.
     * @return el Direccion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    public DireccionDTO agregar(DireccionDTO direccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Direccion");

        //  Valida Entrada
        if (direccionDTO == null) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Direccion direccion = mapper.toEntity(direccionDTO);
            Direccion direccionNuevo = direccionMapper.agregar(direccion);
            Direccion direccionEncontrado = direccionMapper.encontrarPorClave(direccion.getCliente().getId(), direccionNuevo.getId());
            logeador.info("Direccion agregado exitosamente cliente: {}, id: {}", direccion.getCliente().getId(), direccionNuevo.getId());
            return mapper.toDTO(direccionEncontrado);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCION_DUPLICADO_MENSAGE + ": {}, codigoError:{}", direccionDTO.getId(),
                           DireccionError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(DireccionError.DUPLICADO.getCodigoError(),
                                                Constantes.DIRECCION_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_AGREGAR_MENSAJE + ": {}, codigoError:{}", direccionDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Direccion.
     * @param direccionDTOLote lista de Direccion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso direccion ya existe.
     */
    public void agregarLote(List<DireccionDTO> direccionDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() direccion");

        //  Valida Entrada
        if (direccionDTOLote.isEmpty()) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Direccion> direccionLote = mapper.toEntityList(direccionDTOLote);

            int registrosAgregados =  direccionMapper.agregarLote(direccionLote);
            logeador.info("Lote Direccion agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCION_DUPLICADO_MENSAGE + " codigoError:{}",
                           DireccionError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(DireccionError.DUPLICADO.getCodigoError(),
                                                Constantes.DIRECCION_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.DIRECCION_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Direccion existente.
     * @param clienteId La clave de Cliente a actualizar.
     * @param id la clave de Direccion a actualizar.
     * @param direccionDTO el Direccion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizar(Long clienteId, Long id, DireccionDTO direccionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() direccion");

        //  Valida Entrada
        if (id == null || direccionDTO == null || direccionDTO.getId() == null || direccionDTO.getId() .toString().isEmpty() || direccionDTO.getCliente().getId().toString().isEmpty() ) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((direccionDTO != null) ? direccionDTO.toString() : null  ),
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(direccionDTO.getId())) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {} , codigoError:{}",  direccionDTO.toString(),
                           DireccionError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.ID_INVALIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida clienteId
        if (!clienteId.equals(direccionDTO.getCliente().getId())) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {} , codigoError:{}",  direccionDTO.toString(),
                    ClienteError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ClienteError.ID_INVALIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            DireccionDTO direccionDTOEncontrado = this.encontrarPorClave(direccionDTO.getCliente().getId(), id); // Verifica si existe el recurso
            Direccion direccion = mapper.toEntity(direccionDTO);
            direccion.setId(id);
            int registrosActualizados = direccionMapper.actualizar(direccion);
            logeador.info("direccion actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ACTUALIZAR_MENSAJE + ": id={}, {}, codigoError:{} ", id, direccionDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError() , e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Direccion existentes.
     * @param direccionDTOLote lista de Direccion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizarLote(List<DireccionDTO> direccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() direccion");

        //  Valida Entrada
        if (direccionDTOLote.isEmpty()) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Direccion> direccionLote = mapper.toEntityList(direccionDTOLote);
            int registrosActualizados = direccionMapper.actualizarLote(direccionLote);
            logeador.info("Lote direccion actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Direccion por Clave.
     * @param clienteId La clave de Cliente a eliminar.
     * @param  id clave de Direccion a eliminar.
     * @throws RecursoNoEncontradoException si el Direccion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long clienteId, Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() direccion: {}, {}", clienteId, id  );

        //Verifica integridad referencial
//        this.verificarIntegridadEliminar(id);

        try {
            DireccionDTO direccionDTO = this.encontrarPorClave(clienteId, id); // Verifica si existe
            int registrosEliminados = direccionMapper.eliminar(clienteId, id);
            logeador.info("direccion eliminado: {}, {} registros eliminados: {}", clienteId, id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ELIMINAR_MENSAJE + ": cliente: {}, id: {}, codigoError:{}", clienteId, id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Direccion por Clave.
     * @param direccionDTOLote lista de claves de Direccion a eliminar.
     * @throws EntradaInvalidadException si la lista  Direccion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<DireccionDTO> direccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (direccionDTOLote.isEmpty()) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                                                Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
//        for (Long id : idLote) {
//            this.verificarIntegridadEliminar(id);
//        }

        try {
            int registrosEliminados = direccionMapper.eliminarLote(mapper.toEntityList(direccionDTOLote));
            logeador.info("Lote direccion eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ELIMINAR_MENSAJE + " codigoError:{}", GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.DIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Direccion por Clave.
     * @param clientId La clave de Cliente a encontrar.
     * @param id La clave de Direccion a encontrar.
     * @return el Direccion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     */
    public DireccionDTO encontrarPorClave(Long clientId, Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}, {}", clientId, id);

        try {
            DireccionDTO direccionDTO = mapper.toDTO(direccionMapper.encontrarPorClave(clientId,id));

            if (direccionDTO != null) {
                logeador.info("direccion encontrado por clave cliente: {}, id: {}", clientId, id );
            } else {
                logeador.info("direccion clave:{}, {} no encontrado codigoError:{}", clientId, id,
                              DireccionError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(DireccionError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.DIRECCION_NO_ENCONTRADO_MENSAGE);
            }

            return direccionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE + " cliente: {}, id: {}, codigoError:{} ", clientId, id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Direccions.
     * @return una lista de todos Direccion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DireccionDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<DireccionDTO> direccionLista = mapper.toDTOList(direccionMapper.obtenerTodos());
            logeador.info("direccions obtenidos");
            return direccionLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.DIRECCION_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de Direccion
     * @param id la clave Direccion a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
//    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
//        logeador.debug("verificarIntegridadEliminar() direccion: {}", id);
//
//        boolean entityRelacionadoPorDireccion = false;
//
//        try {
//            entityRelacionadoPorDireccion = this.entityRelacionadoPorDireccion(id);
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.DIRECCION_ELIMINAR_MENSAJE + ": {}", id, e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.DIRECCION_ELIMINAR_MENSAJE, e);
//        }
//
//        //Verifca la integridad con sectores
//        if (entityRelacionadoPorDireccion) {
//            throw new RecursoEliminarException(DireccionError.INTEGRIDAD_VIOLADA.getCodigoError(),
//                                               Constantes.DIRECCION_VIOLACION_INTEGRIDAD_MENSAGE);
//        }
//
//    }

    /**
     * Buscar Direccion que tengan EntityRelacionado.
     * @param id la clave Direccion a encontrar.
     * @return boolean Direccion tiene o no registros asociados
     * @throws BaseDatosException
     */
//    public boolean entityRelacionadoPorDireccion(Long id) throws  BaseDatosException {
//        logeador.debug("entityRelacionadoPorDireccion() direccion: {}", id);
//
//        try {
//            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorDireccion(id); // Verifica si tiene EntityRelacionado  asociados
//            if (!entitys.isEmpty()) {
//                logeador.info("direccion  tiene #EntityRelacionado# asociados");
//                return true;
//            } else{
//                logeador.info("direccion no tiene #EntityRelacionado# asociados");
//                return false;
//            }
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.DIRECCION_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}", id, e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.DIRECCION_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
//        }
//    }
}