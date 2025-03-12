package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ServicioTrabajoDTO;
import com.elitsoft.servicampo.domain.entity.ServicioTrabajo;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ServicioTrabajoMapper;
import com.elitsoft.servicampo.mapstruct.ServicioTrabajoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ServicioTrabajoError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad ServicioTrabajo.
 */
@Service
public class ServicioTrabajoService {

    @Autowired
    private ServicioTrabajoMapper serviciotrabajoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ServicioTrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ServicioTrabajoService.class); //Logback

    /**
     * Agrega un nuevo ServicioTrabajo.
     * @param serviciotrabajoDTO el ServicioTrabajo DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso serviciotrabajo ya existe.
     */
    public void agregar(ServicioTrabajoDTO serviciotrabajoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() serviciotrabajo");

        //  Valida Entrada
        if (serviciotrabajoDTO == null || serviciotrabajoDTO.getServicio()  == null || serviciotrabajoDTO.getServicio().getId() == null
                                       || serviciotrabajoDTO.getTrabajo()   == null || serviciotrabajoDTO.getTrabajo().getId()  == null
                                       || serviciotrabajoDTO.getSecuencia() ==  null  ) {
            logeador.error(Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((serviciotrabajoDTO != null) ? serviciotrabajoDTO.toString() : null  ),
                           ServicioTrabajoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioTrabajoError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ServicioTrabajo serviciotrabajo = mapper.toEntity(serviciotrabajoDTO);
            Long nuevoId = serviciotrabajoMapper.agregar(serviciotrabajo);
            logeador.info("ServicioTrabajo agregado exitosamente id: {}", nuevoId);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_DUPLICADO_MENSAGE + ": {}, {} codigoError:{}", serviciotrabajoDTO.getServicio().getId(),
                           serviciotrabajoDTO.getTrabajo().getId(), ServicioTrabajoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ServicioTrabajoError.DUPLICADO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_AGREGAR_MENSAJE + ": {}, codigoError:{}", serviciotrabajoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIOTRABAJO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega un nuevo ServicioTrabajo.
     * @param serviciotrabajoDTO el ServicioTrabajo DTO.
     * @return el ServicioTrabajo DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso ServicioTrabajo ya existe.
     */
//    public ServicioTrabajoDTO agregar(ServicioTrabajoDTO serviciotrabajoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
//        logeador.debug("agregar() ServicioTrabajo");
//
//        //  Valida Entrada
//        if (serviciotrabajoDTO == null) {
//            logeador.error(Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
//                           ServicioTrabajoError.REQUERIDO.getCodigoError());
//            throw new EntradaInvalidadException(ServicioTrabajoError.REQUERIDO.getCodigoError(),
//                                                Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE);
//        }
//
//        try {
//            ServicioTrabajo serviciotrabajo = mapper.toEntity(serviciotrabajoDTO);
//            serviciotrabajo = serviciotrabajoMapper.agregar(serviciotrabajo);
//            logeador.info("ServicioTrabajo agregado exitosamente id: {}", serviciotrabajo.getId());
//            return mapper.toDTO(serviciotrabajo);
//        }
//        catch (DuplicateKeyException e) {
//            logeador.error(Constantes.SERVICIOTRABAJO_DUPLICADO_MENSAGE + ": {}, codigoError:{}", serviciotrabajoDTO.getId(),
//                           ServicioTrabajoError.DUPLICADO.getCodigoError());
//            throw new RecursoDuplicadoException(ServicioTrabajoError.DUPLICADO.getCodigoError(),
//                                                Constantes.SERVICIOTRABAJO_DUPLICADO_MENSAGE);
//        }
//        catch (DataAccessException e) {
//            logeador.error(Constantes.SERVICIOTRABAJO_AGREGAR_MENSAJE + ": {}, codigoError:{}", serviciotrabajoDTO.toString(),
//                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.SERVICIOTRABAJO_AGREGAR_MENSAJE, e);
//        }
//    }

    /**
     * Agrega Lote nuevos ServicioTrabajo.
     * @param serviciotrabajoDTOLote lista de ServicioTrabajo DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso serviciotrabajo ya existe.
     */
    public void agregarLote(List<ServicioTrabajoDTO> serviciotrabajoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() serviciotrabajo");

        //  Valida Entrada
        if (serviciotrabajoDTOLote.isEmpty()) {
            logeador.error(Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          ServicioTrabajoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioTrabajoError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<ServicioTrabajo> serviciotrabajoLote = mapper.toEntityList(serviciotrabajoDTOLote);

            int registrosAgregados =  serviciotrabajoMapper.agregarLote(serviciotrabajoLote);
            logeador.info("Lote ServicioTrabajo agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_DUPLICADO_MENSAGE + " codigoError:{}",
                          ServicioTrabajoError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(ServicioTrabajoError.DUPLICADO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.SERVICIOTRABAJO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un ServicioTrabajo existente.
     * @param servicioId La clave de Servicio a eliminar.
     * @param trabajoId La clave de Trabajo a eliminar.
     * @param serviciotrabajoDTO el ServicioTrabajo DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ServicioTrabajo no es encontrado.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     */
    public void actualizar(Long servicioId, Long trabajoId, ServicioTrabajoDTO serviciotrabajoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() serviciotrabajo {}, {}",servicioId,trabajoId );

        //  Valida Entrada
        if (servicioId == null || trabajoId == null  || serviciotrabajoDTO == null || serviciotrabajoDTO.getServicio()  == null
                        || serviciotrabajoDTO.getServicio().getId() == null || serviciotrabajoDTO.getTrabajo() == null
                        || serviciotrabajoDTO.getTrabajo().getId()  == null || serviciotrabajoDTO.getSecuencia() ==  null) {
            logeador.error(Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((serviciotrabajoDTO != null) ? serviciotrabajoDTO.toString() : null  ),
                           ServicioTrabajoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioTrabajoError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }


        //  Valida id
        if (!servicioId.equals(serviciotrabajoDTO.getServicio().getId()) || !trabajoId.equals(serviciotrabajoDTO.getTrabajo().getId())) {
            logeador.error(Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  serviciotrabajoDTO.toString(),
                           ServicioTrabajoError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioTrabajoError.ID_INVALIDO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ServicioTrabajoDTO serviciotrabajoDTOEncontrado = this.encontrarPorClave(servicioId,trabajoId); // Verifica si existe el recurso
            ServicioTrabajo serviciotrabajo = mapper.toEntity(serviciotrabajoDTO);
            int registrosActualizados = serviciotrabajoMapper.actualizar(serviciotrabajo);
            logeador.info("serviciotrabajo actualizado exitosamente: {},{} registros actualizados: {}", servicioId, trabajoId, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_ACTUALIZAR_MENSAJE + ": servicioId={},  trabajoId={} codigoError:{}", servicioId, trabajoId, serviciotrabajoDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIOTRABAJO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de ServicioTrabajo existentes.
     * @param serviciotrabajoDTOLote lista de ServicioTrabajo DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ServicioTrabajo tiene errores.
     */
    public void actualizarLote(List<ServicioTrabajoDTO> serviciotrabajoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() serviciotrabajo");

        //  Valida Entrada
        if (serviciotrabajoDTOLote.isEmpty()) {
            logeador.error(Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           ServicioTrabajoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioTrabajoError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<ServicioTrabajo> serviciotrabajoLote = mapper.toEntityList(serviciotrabajoDTOLote);
            int registrosActualizados = serviciotrabajoMapper.actualizarLote(serviciotrabajoLote);
            logeador.info("Lote serviciotrabajo actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIOTRABAJO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina ServicioTrabajo por Clave.
     * @param servicioId La clave de Servicio a eliminar.
     * @param trabajoId La clave de Trabajo a eliminar.
     * @throws RecursoNoEncontradoException si el ServicioTrabajo no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long servicioId, Long trabajoId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() serviciotrabajo: {}, {} ", servicioId, trabajoId);

        //Verifica integridad referencial
        //this.verificarIntegridadEliminar(id);

        try {
            ServicioTrabajoDTO serviciotrabajoDTO = this.encontrarPorClave(servicioId, trabajoId); // Verifica si existe
            int registrosEliminados = serviciotrabajoMapper.eliminar(servicioId,trabajoId );
            logeador.info("serviciotrabajo eliminado: {}, {} registros eliminados: {}", servicioId, trabajoId, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_ELIMINAR_MENSAJE + ": {}, {} codigoError:{}", servicioId, trabajoId,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIOTRABAJO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote ServicioTrabajo por Clave.
     * @param serviciotrabajoDTOLote lista de claves de ServicioTrabajo a eliminar.
     * @throws EntradaInvalidadException si la lista  ServicioTrabajo esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ServicioTrabajoDTO> serviciotrabajoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (serviciotrabajoDTOLote.isEmpty()) {
            logeador.error(Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           ServicioTrabajoError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ServicioTrabajoError.REQUERIDO.getCodigoError(),
                                                Constantes.SERVICIOTRABAJO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
        //for (Long id : idLote) {
        //    this.verificarIntegridadEliminar(id);
        //}

        try {
            int registrosEliminados = serviciotrabajoMapper.eliminarLote(mapper.toEntityList(serviciotrabajoDTOLote));
            logeador.info("Lote serviciotrabajo eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.SERVICIOTRABAJO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un ServicioTrabajo por Clave.
     * @param servicioId La clave de Servicio a eliminar.
     * @param trabajoId La clave de Trabajo a eliminar.
     * @return el ServicioTrabajo DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ServicioTrabajo no es encontrado.
     */
    public ServicioTrabajoDTO encontrarPorClave(Long servicioId, Long trabajoId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}, {}", servicioId,trabajoId);

        try {
            ServicioTrabajoDTO serviciotrabajoDTO = mapper.toDTO(serviciotrabajoMapper.encontrarPorClave(servicioId,trabajoId));

            if (serviciotrabajoDTO != null) {
                logeador.info("serviciotrabajo encontrado por clave : {}, {}", servicioId,trabajoId );
            } else {
                logeador.info("serviciotrabajo clave:{}, {} no encontrado codigoError:{}", servicioId, trabajoId,
                              ServicioTrabajoError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(ServicioTrabajoError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.SERVICIOTRABAJO_NO_ENCONTRADO_MENSAGE);
            }

            return serviciotrabajoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, {} codigoError:{}", servicioId,trabajoId,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIOTRABAJO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los ServicioTrabajos.
     * @return una lista de todos ServicioTrabajo DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ServicioTrabajoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ServicioTrabajoDTO> serviciotrabajoLista = mapper.toDTOList(serviciotrabajoMapper.obtenerTodos());
            logeador.info("serviciotrabajos obtenidos");
            return serviciotrabajoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.SERVICIOTRABAJO_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de ServicioTrabajo
     * @param id la clave ServicioTrabajo a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
    /*
    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
        logeador.debug("verificarIntegridadEliminar() serviciotrabajo: {}", id);

        boolean entityRelacionadoPorServicioTrabajo = false;

        try {
            entityRelacionadoPorServicioTrabajo = this.entityRelacionadoPorServicioTrabajo(id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIOTRABAJO_ELIMINAR_MENSAJE, e);
        }

        //Verifca la integridad con sectores
        if (entityRelacionadoPorServicioTrabajo) {
            throw new RecursoEliminarException(ServicioTrabajoError.INTEGRIDAD_VIOLADA.getCodigoError(),
                                               Constantes.SERVICIOTRABAJO_VIOLACION_INTEGRIDAD_MENSAGE);
        }

    }
    */

    /**
     * Buscar ServicioTrabajo que tengan EntityRelacionado.
     * @param id la clave ServicioTrabajo a encontrar.
     * @return boolean ServicioTrabajo tiene o no registros asociados
     * @throws BaseDatosException
     */
    /*
    public boolean entityRelacionadoPorServicioTrabajo(Long id) throws  BaseDatosException {
        logeador.debug("entityRelacionadoPorServicioTrabajo() serviciotrabajo: {}", id);

        try {
            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorServicioTrabajo(id); // Verifica si tiene EntityRelacionado  asociados
            if (!entitys.isEmpty()) {
                logeador.info("serviciotrabajo  tiene #EntityRelacionado# asociados");
                return true;
            } else{
                logeador.info("serviciotrabajo no tiene #EntityRelacionado# asociados");
                return false;
            }
        } catch (DataAccessException e) {
            logeador.error(Constantes.SERVICIOTRABAJO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.SERVICIOTRABAJO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }
    */
}