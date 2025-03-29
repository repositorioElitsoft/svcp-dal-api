package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ServicioDTO;
import com.elitsoft.servicampo.domain.entity.Servicio;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ServicioMapper;
import com.elitsoft.servicampo.mapstruct.ServicioMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ServicioError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Servicio.
 */
@Service
public class ServicioService {

    @Autowired
    private ServicioMapper servicioMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ServicioService.class); //Logback


    /**
     * Agrega un nuevo Servicio.
     * @param servicioDTO el Servicio DTO.
     * @return el Servicio DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso Servicio ya existe.
     */
    public ServicioDTO agregar(ServicioDTO servicioDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() Servicio");

        //  Valida Entrada
        if (servicioDTO == null) {
            logeador.error(Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           ServicioError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Servicio servicio = servicioMapper.agregar(mapper.toEntity(servicioDTO));
            ServicioDTO servicioDTOEncontrado = this.encontrarPorClave(servicio.getId());
            logeador.info("Servicio agregado exitosamente id: {}", servicio.getId());
            return servicioDTOEncontrado;
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.SERVICIO_DUPLICADO_MENSAGE + ": {}, codigoError:{}", servicioDTO.getId(),
                           ServicioError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ServicioError.DUPLICADO.getCodigoError(),
                                                Constantes.SERVICIO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIO_AGREGAR_MENSAJE + ": {}, codigoError:{}", servicioDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Servicio.
     * @param servicioDTOLote lista de Servicio DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso servicio ya existe.
     */
    public void agregarLote(List<ServicioDTO> servicioDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() servicio");

        //  Valida Entrada
        if (servicioDTOLote.isEmpty()) {
            logeador.error(Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          ServicioError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Servicio> servicioLote = mapper.toEntityList(servicioDTOLote);

            int registrosAgregados =  servicioMapper.agregarLote(servicioLote);
            logeador.info("Lote Servicio agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.SERVICIO_DUPLICADO_MENSAGE + " codigoError:{}",
                          ServicioError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ServicioError.DUPLICADO.getCodigoError(),
                                                Constantes.SERVICIO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.SERVICIO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Servicio existente.
     * @param id la clave de Servicio a actualizar.
     * @param servicioDTO el Servicio DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Servicio no es encontrado.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     */
    public void actualizar(Long id, ServicioDTO servicioDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() servicio");

        //  Valida Entrada
        if (id == null || servicioDTO == null || servicioDTO.getId() == null) {
            logeador.error(Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((servicioDTO != null) ? servicioDTO.toString() : null  ),
                           ServicioError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(servicioDTO.getId())) {
            logeador.error(Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  servicioDTO.toString(),
                           ServicioError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioError.ID_INVALIDO.getCodigoError(),
                                                Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ServicioDTO servicioDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Servicio servicio = mapper.toEntity(servicioDTO);
            servicio.setId(id);
            int registrosActualizados = servicioMapper.actualizar(servicio);
            logeador.info("servicio actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIO_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, servicioDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Servicio existentes.
     * @param servicioDTOLote lista de Servicio DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     */
    public void actualizarLote(List<ServicioDTO> servicioDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() servicio");

        //  Valida Entrada
        if (servicioDTOLote.isEmpty()) {
            logeador.error(Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           ServicioError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Servicio> servicioLote = mapper.toEntityList(servicioDTOLote);
            int registrosActualizados = servicioMapper.actualizarLote(servicioLote);
            logeador.info("Lote servicio actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Servicio por Clave.
     * @param id la clave de Servicio a eliminar.
     * @throws RecursoNoEncontradoException si el Servicio no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() servicio: {}", id);

        //Verifica integridad referencial
//        this.verificarIntegridadEliminar(id);

        try {
            ServicioDTO servicioDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = servicioMapper.eliminar(id);
            logeador.info("servicio eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Servicio por Clave.
     * @param servicioDTOLote lista de claves de Direccion a eliminar.
     * @throws EntradaInvalidadException si la lista  Servicio esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ServicioDTO> servicioDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (servicioDTOLote.isEmpty()) {
            logeador.error(Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           ServicioError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
//        for (Long id : idLote) {
//            this.verificarIntegridadEliminar(id);
//        }

        try {
            int registrosEliminados = servicioMapper.eliminarLote(mapper.toEntityList(servicioDTOLote));
            logeador.info("Lote servicio eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.SERVICIO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Servicio por Clave.
     * @param id la clave Servicio a encontrar.
     * @return el Servicio DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Servicio no es encontrado.
     */
    public ServicioDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ServicioDTO servicioDTO = mapper.toDTO(servicioMapper.encontrarPorClave(id));

            if (servicioDTO != null) {
                logeador.info("servicio encontrado por clave : {}", id);
            } else {
                logeador.info("servicio clave:{} no encontrado codigoError:{}", id,
                              ServicioError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ServicioError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.SERVICIO_NO_ENCONTRADO_MENSAGE);
            }

            return servicioDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Servicios.
     * @return una lista de todos Servicio DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ServicioDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ServicioDTO> servicioLista = mapper.toDTOList(servicioMapper.obtenerTodos());
            logeador.info("servicios obtenidos");
            return servicioLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.SERVICIO_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de Servicio
     * @param id la clave Servicio a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
//    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
//        logeador.debug("verificarIntegridadEliminar() servicio: {}", id);
//
//        boolean entityRelacionadoPorServicio = false;
//
//        try {
//            entityRelacionadoPorServicio = this.entityRelacionadoPorServicio(id);
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.SERVICIO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
//                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.SERVICIO_ELIMINAR_MENSAJE, e);
//        }
//
//        //Verifca la integridad con sectores
//        if (entityRelacionadoPorServicio) {
//            throw new RecursoEliminarException(ServicioError.INTEGRIDAD_VIOLADA.getCodigoError(),
//                                               Constantes.SERVICIO_VIOLACION_INTEGRIDAD_MENSAGE);
//        }
//
//    }

    /**
     * Buscar Servicio que tengan EntityRelacionado.
     * @param id la clave Servicio a encontrar.
     * @return boolean Servicio tiene o no registros asociados
     * @throws BaseDatosException
     */
//    public boolean entityRelacionadoPorServicio(Long id) throws  BaseDatosException {
//        logeador.debug("entityRelacionadoPorServicio() servicio: {}", id);
//
//        try {
//            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorServicio(id); // Verifica si tiene EntityRelacionado  asociados
//            if (!entitys.isEmpty()) {
//                logeador.info("servicio  tiene #EntityRelacionado# asociados");
//                return true;
//            } else{
//                logeador.info("servicio no tiene #EntityRelacionado# asociados");
//                return false;
//            }
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.SERVICIO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}, codigoError:{}", id,
//                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.SERVICIO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
//        }
//    }
}