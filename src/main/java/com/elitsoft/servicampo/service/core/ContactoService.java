package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.domain.entity.Contacto;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ContactoMapper;
import com.elitsoft.servicampo.mapstruct.ContactoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.service.error.ContactoError;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase de Servicio para la entidad Contacto.
 */
@Service
public class ContactoService {

    @Autowired
    private ContactoMapper contactoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContactoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContactoService.class); //Logback


    /**
     * Agrega un nuevo Contacto.
     * @param contactoDTO el Contacto DTO.
     * @return el Contacto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Contacto ya existe.
     */
    public ContactoDTO agregar(ContactoDTO contactoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() Contacto");

        //  Valida Entrada
        if (contactoDTO == null) {
            logeador.error(Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ContactoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            Contacto contacto = mapper.toEntity(contactoDTO);
            contacto = contactoMapper.agregar(contacto);
            logeador.info("Contacto agregado exitosamente id: {}", contacto.getId());
            return mapper.toDTO(contacto);
        }
        catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTACTO_DUPLICADO_MENSAGE + ": {}", contactoDTO.getId());
            throw new RecursoDuplicadoException(ContactoError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTACTO_DUPLICADO_MENSAGE);
        }
        catch (DataAccessException e) {
            logeador.error(Constantes.CONTACTO_AGREGAR_MENSAJE + ": {}", contactoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTACTO_AGREGAR_MENSAJE, e);
        }
    }

    /**
     * Agrega Lote nuevos Contacto.
     * @param contactoDTOLote lista de Contacto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     * @throws RecursoDuplicadoException si el recurso contacto ya existe.
     */
    public void agregarLote(List<ContactoDTO> contactoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() contacto");

        //  Valida Entrada
        if (contactoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ContactoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
        }
        try {
            List<Contacto> contactoLote = mapper.toEntityList(contactoDTOLote);

            int registrosAgregados =  contactoMapper.agregarLote(contactoLote);
            logeador.info("Lote Contacto agregados exitosamente,  registros agregados: {}", registrosAgregados);
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.CONTACTO_DUPLICADO_MENSAGE);
            throw new RecursoDuplicadoException(ContactoError.DUPLICADO.getCodigoError(),
                                                Constantes.CONTACTO_DUPLICADO_MENSAGE);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTACTO_AGREGAR_LOTE_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTACTO_AGREGAR_LOTE_MENSAJE, e);
        }
    }

    /**
     * Actualiza un Contacto existente.
     * @param id la clave de Contacto a actualizar.
     * @param contactoDTO el Contacto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contacto no es encontrado.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     */
    public void actualizar(Long id, ContactoDTO contactoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException {
        logeador.debug("actualizar() contacto");

        //  Valida Entrada
        if (id == null || contactoDTO == null || contactoDTO.getId() == null) {
            logeador.error(Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE + ": {}", ((contactoDTO != null) ? contactoDTO.toString() : null  ));
            throw new EntradaInvalidadException(ContactoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida id
        if (!id.equals(contactoDTO.getId())) {
            logeador.error(Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE + ": {}",  contactoDTO.toString());
            throw new EntradaInvalidadException(ContactoError.ID_INVALIDO.getCodigoError(),
                                                Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            ContactoDTO contactoDTOEncontrado = this.encontrarPorClave(id); // Verifica si existe el recurso
            Contacto contacto = mapper.toEntity(contactoDTO);
            contacto.setId(id);
            int registrosActualizados = contactoMapper.actualizar(contacto);
            logeador.info("contacto actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTACTO_ACTUALIZAR_MENSAJE + ": id={} {}", id, contactoDTO.toString(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTACTO_ACTUALIZAR_MENSAJE, e);
        }
    }

   /**
     * Actualiza Lote de Contacto existentes.
     * @param contactoDTOLote lista de Contacto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     */
    public void actualizarLote(List<ContactoDTO> contactoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() contacto");

        //  Valida Entrada
        if (contactoDTOLote.isEmpty()) {
            logeador.error(Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ContactoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
        }

        try {
            List<Contacto> contactoLote = mapper.toEntityList(contactoDTOLote);
            int registrosActualizados = contactoMapper.actualizarLote(contactoLote);
            logeador.info("Lote contacto actualizados exitosamente, registros actualizados: {}", registrosActualizados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTACTO_ACTUALIZAR_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTACTO_ACTUALIZAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Contacto por Clave.
     * @param id la clave de Contacto a eliminar.
     * @throws RecursoNoEncontradoException si el Contacto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() contacto: {}", id);

        //Verifica integridad referencial
//        this.verificarIntegridadEliminar(id);

        try {
            ContactoDTO contactoDTO = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = contactoMapper.eliminar(id);
            logeador.info("contacto eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTACTO_ELIMINAR_MENSAJE + ": {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTACTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Elimina Lote Contacto por Clave.
     * @param idLote lista de claves de Contacto a eliminar.
     * @throws EntradaInvalidadException si la lista  Contacto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");


        //  Valida Entrada
        if (idLote.isEmpty()) {
            logeador.error(Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
            throw new EntradaInvalidadException(ContactoError.REQUERIDO.getCodigoError(),
                                                Constantes.CONTACTO_ENTRADA_INVALIDA_MENSAGE);
        }

        //Verifica integridad referencial
//        for (Long id : idLote) {
//            this.verificarIntegridadEliminar(id);
//        }

        try {
            int registrosEliminados = contactoMapper.eliminarLote(idLote);
            logeador.info("Lote contacto eliminados exitosamente, registros eliminados: {}", registrosEliminados);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.CONTACTO_ELIMINAR_MENSAJE,  e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTACTO_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra un Contacto por Clave.
     * @param id la clave Contacto a encontrar.
     * @return el Contacto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contacto no es encontrado.
     */
    public ContactoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ContactoDTO contactoDTO = mapper.toDTO(contactoMapper.encontrarPorClave(id));

            if (contactoDTO != null) {
                logeador.info("contacto encontrado por clave : {}", id);
            } else {
                logeador.info("contacto clave:{} no encontrado", id);
                throw new RecursoNoEncontradoException(ContactoError.NO_ENCONTRADO.getCodigoError(),
                                                       Constantes.CONTACTO_NO_ENCONTRADO_MENSAGE);
            }

            return contactoDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTACTO_ENCONTRAR_POR_CLAVE_MENSAGE + " {}", id, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                         Constantes.CONTACTO_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

    /**
     * Obtiene todos los Contactos.
     * @return una lista de todos Contacto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContactoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ContactoDTO> contactoLista = mapper.toDTOList(contactoMapper.obtenerTodos());
            logeador.info("contactos obtenidos");
            return contactoLista;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CONTACTO_OBTENER_TODOS_MENSAJE, e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                                        Constantes.CONTACTO_OBTENER_TODOS_MENSAJE, e);
        }
    }

        /**
     * Verifica la violacion de integridad referencia de Contacto
     * @param id la clave Contacto a encontrar.
     * @throws RecursoEliminarException
     * @throws BaseDatosException
     */
//    public void verificarIntegridadEliminar(Long id) throws  RecursoEliminarException, BaseDatosException {
//        logeador.debug("verificarIntegridadEliminar() contacto: {}", id);
//
//        boolean entityRelacionadoPorContacto = false;
//
//        try {
//            entityRelacionadoPorContacto = this.entityRelacionadoPorContacto(id);
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.CONTACTO_ELIMINAR_MENSAJE + ": {}", id, e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.CONTACTO_ELIMINAR_MENSAJE, e);
//        }
//
//        //Verifca la integridad con sectores
//        if (entityRelacionadoPorContacto) {
//            throw new RecursoEliminarException(ContactoError.INTEGRIDAD_VIOLADA.getCodigoError(),
//                                               Constantes.CONTACTO_VIOLACION_INTEGRIDAD_MENSAGE);
//        }
//
//    }

    /**
     * Buscar Contacto que tengan EntityRelacionado.
     * @param id la clave Contacto a encontrar.
     * @return boolean Contacto tiene o no registros asociados
     * @throws BaseDatosException
     */
//    public boolean entityRelacionadoPorContacto(Long id) throws  BaseDatosException {
//        logeador.debug("entityRelacionadoPorContacto() contacto: {}", id);
//
//        try {
//            List<EntityRelacionado> entitys = entityRelacionadoMapper.encontrarPorContacto(id); // Verifica si tiene EntityRelacionado  asociados
//            if (!entitys.isEmpty()) {
//                logeador.info("contacto  tiene #EntityRelacionado# asociados");
//                return true;
//            } else{
//                logeador.info("contacto no tiene #EntityRelacionado# asociados");
//                return false;
//            }
//        } catch (DataAccessException e) {
//            logeador.error(Constantes.CONTACTO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE + ": {}", id, e);
//            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
//                                         Constantes.CONTACTO_SECTOR_ENCONTRAR_POR_CLAVE_MENSAGE, e);
//        }
//    }
}