package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.RutaDTO;
import com.elitsoft.servicampo.domain.entity.Ruta;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.RutaMapper;
import com.elitsoft.servicampo.mapstruct.RutaMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.RutaError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Ruta.
 */
@Service
public class RutaService {

    @Autowired
    private RutaMapper rutaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RutaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(RutaService.class); //Logback


    /**
     * Agrega un nuevo Ruta.
     * @param rutaDTO el Ruta DTO.
     * @return el Ruta DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     * @throws RecursoDuplicadoException si el recurso Ruta ya existe.
     */
    public RutaDTO agregar(RutaDTO rutaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Ruta");

        //  Valida Entrada
        if (rutaDTO == null) {
            logeador.error(Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                           RutaError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(RutaError.REQUERIDO.getCodigoError(),
                                                Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Ruta ruta = mapper.toEntity(rutaDTO);
            ruta = rutaMapper.agregar(ruta);
            logeador.info("Ruta agregado exitosamente id: {}", ruta.getId());
            return mapper.toDTO(ruta);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.RUTA_DUPLICADO_MENSAGE + ": {}, codigoError:{}", rutaDTO.getId(),
                           RutaError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(RutaError.DUPLICADO.getCodigoError(),
                                                Constantes.RUTA_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.RUTA_AGREGAR_MENSAJE + ": {}, codigoError:{}", rutaDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.RUTA_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Ruta.
     * @param rutaDTOLote lista de Ruta DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     * @throws RecursoDuplicadoException si el recurso ruta ya existe.
     */
    public void agregarLote(List<RutaDTO> rutaDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() ruta");

        //  Valida Entrada
        if (rutaDTOLote.isEmpty()) {
            logeador.error(Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                          RutaError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(RutaError.REQUERIDO.getCodigoError(),
                                                Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Ruta> rutaLote = mapper.toEntityList(rutaDTOLote);

            int registrosAgregados =  rutaMapper.agregarLote(rutaLote);
            logeador.info("Lote Ruta agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.RUTA_DUPLICADO_MENSAGE + " codigoError:{}",
                          RutaError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(RutaError.DUPLICADO.getCodigoError(),
                                                Constantes.RUTA_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.RUTA_AGREGAR_LOTE_MENSAJE + " codigoError:{}",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.RUTA_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Ruta existente.
     * @param id la clave de Ruta a actualizar.
     * @param rutaDTO el Ruta DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Ruta no es encontrado.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     */
    public void actualizar(Long id, RutaDTO rutaDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() ruta");

        //  Valida Entrada
        if (id == null || rutaDTO == null || rutaDTO.getId() == null) {
            logeador.error(Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((rutaDTO != null) ? rutaDTO.toString() : null  ),
                           RutaError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(RutaError.REQUERIDO.getCodigoError(),
                                                Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(rutaDTO.getId())) {
            logeador.error(Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}",  rutaDTO.toString(),
                           RutaError.ID_INVALIDO.getCodigoError());
            throw new EntradaInvalidadException(RutaError.ID_INVALIDO.getCodigoError(),
                                                Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            RutaDTO rutaDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Ruta ruta = mapper.toEntity(rutaDTO);
            ruta.setId(id);
            int registrosActualizados = rutaMapper.actualizar(ruta);
            logeador.info("ruta actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.RUTA_ACTUALIZAR_MENSAJE + ": id={} {} codigoError:{}", id, rutaDTO.toString(),
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.RUTA_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Ruta existentes.
     * @param rutaDTOLote lista de Ruta DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     */
    public void actualizarLote(List<RutaDTO> rutaDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() ruta");

        //  Valida Entrada
        if (rutaDTOLote.isEmpty()) {
            logeador.error(Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ", 
                           RutaError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(RutaError.REQUERIDO.getCodigoError(),
                                                Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Ruta> rutaLote = mapper.toEntityList(rutaDTOLote);
            int registrosActualizados = rutaMapper.actualizarLote(rutaLote);
            logeador.info("Lote ruta actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.RUTA_ACTUALIZAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.RUTA_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Ruta por Clave.
     * @param id la clave de Ruta a eliminar.
     * @throws RecursoNoEncontradoException si el Ruta no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() ruta: {}", id);

        //Verifica integridad referencial
        //this.verificarIntegridadEliminar(id);

        try {
            RutaDTO rutaDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = rutaMapper.eliminar(id);
            logeador.info("ruta eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.RUTA_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.RUTA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Ruta por Clave.
     * @param rutaDTOLote lista de claves de Ruta a eliminar.
     * @throws EntradaInvalidadException si la lista  Ruta esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<RutaDTO> rutaDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (rutaDTOLote.isEmpty()) {
            logeador.error(Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE + " codigoError:{}",
                           RutaError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(RutaError.REQUERIDO.getCodigoError(),
                                                Constantes.RUTA_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
        //for (Long id : idLote) {
        //    this.verificarIntegridadEliminar(id);
        //}

        try {
            int registrosEliminados = rutaMapper.eliminarLote(mapper.toEntityList(rutaDTOLote));
            logeador.info("Lote ruta eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.RUTA_ELIMINAR_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(),  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.RUTA_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Ruta por Clave.
     * @param id la clave Ruta a encontrar.
     * @return el Ruta DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Ruta no es encontrado.
     */
    public RutaDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            RutaDTO rutaDTO = mapper.toDTO(rutaMapper.encontrarPorClave(id));

            if (rutaDTO != null) {
                logeador.info("ruta encontrado por clave : {}", id);
            } else {
                logeador.info("ruta clave:{} no encontrado codigoError:{}", id,
                              RutaError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(RutaError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.RUTA_NO_ENCONTRADO_MENSAGE);
            }

            return rutaDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.RUTA_ENCONTRAR_POR_CLAVE_MENSAGE + " {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.RUTA_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Rutas.
     * @return una lista de todos Ruta DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<RutaDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<RutaDTO> rutaLista = mapper.toDTOList(rutaMapper.obtenerTodos());
            logeador.info("rutas obtenidos");
            return rutaLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.RUTA_OBTENER_TODOS_MENSAJE + " codigoError:{} ",
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.RUTA_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de Ruta
     * @param id la clave Ruta a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
    /*
    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
        logeador.debug("verificarIntegridadEliminar() ruta: {}", id);

        boolean entityRelacionadoPorRuta = false;

        try {
            entityRelacionadoPorRuta = this.entityRelacionadoPorRuta(id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.RUTA_ELIMINAR_MENSAJE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.RUTA_ELIMINAR_MENSAJE, e);
        }

        //Verifca la integridad con sectores
        if (entityRelacionadoPorRuta) {
            throw new RecursoEliminarException(RutaError.INTEGRIDAD_VIOLADA.getCodigoError(),
                                               Constantes.RUTA_VIOLACION_INTEGRIDAD_MENSAGE);
        }

    }
    */

    /**
     * Buscar Ruta que tengan EntityRelacionado.
     * @param id la clave Ruta a encontrar.
     * @return boolean Ruta tiene o no registros asociados
     * @throws BaseDatosException
     */
    /*
    public boolean entityRelacionadoPorRuta(Long id) throws  BaseDatosException {
        logeador.debug("entityRelacionadoPorRuta() ruta: {}", id);

        try {
            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorRuta(id); // Verifica si tiene EntityRelacionado  asociados
            if (!entitys.isEmpty()) {
                logeador.info("ruta  tiene #EntityRelacionado# asociados");
                return true;
            } else{
                logeador.info("ruta no tiene #EntityRelacionado# asociados");
                return false;
            }
        } catch (DataAccessException e) {
            logeador.error(Constantes.RUTA_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}, codigoError:{}", id,
                           GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.RUTA_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }
    */
}