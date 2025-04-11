package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.domain.dto.core.ContactoDireccionDTO;
import com.elitsoft.servicampo.domain.entity.Cliente;
import com.elitsoft.servicampo.domain.entity.Contacto;
import com.elitsoft.servicampo.domain.entity.ContactoDireccion;
import com.elitsoft.servicampo.domain.entity.Direccion;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ContactoDireccionMapper;
import com.elitsoft.servicampo.mapstruct.ContactoDireccionMapStruct;
import com.elitsoft.servicampo.mapstruct.ContactoMapStruct;
import com.elitsoft.servicampo.service.error.ClienteError;
import com.elitsoft.servicampo.service.error.DireccionError;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.utils.Constantes;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de Servicio para la entidad ContactoDireccion.
 */
@Service
public class ContactoDireccionService {

    @Autowired
    private ContactoDireccionMapper contactoDireccionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContactoService contactoService;

    @Autowired
    private ContactoMapStruct contactoMapStruct;

    @Autowired
    private ContactoDireccionMapStruct contactoDireccionMapStruct;


    private static final Logger logeador = LoggerFactory.getLogger(ContactoDireccionService.class); //Logback


    /**
     * Agrega un nuevo Contacto a una Direccion existente.
     *
     * @param id                   clave de Direccion a eliminar.
     * @param clienteId            La clave de Cliente a eliminar.
     * @param contactoDireccionDTO el ContactoDireccion DTO.
     * @return el Direccion DTO existente con Contacto DTO agregado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    @Transactional
    public ContactoDireccionDTO agregarContacto(Long id, Long clienteId, ContactoDireccionDTO contactoDireccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregarContacto()");

        //  Valida Entrada Direccion
        if (id == null) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }

        //  Valida Entrada Cliente
        if (clienteId == null) {
            logeador.error(Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE + " codigoError:{} ",
                    ClienteError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(ClienteError.REQUERIDO.getCodigoError(),
                    Constantes.CLIENTE_ENTRADA_INVALIDA_MENSAGE);
        }


        try {

            Cliente cliente = new Cliente();
            cliente.setId(clienteId);

            Direccion direccion = new Direccion();
            direccion.setId(id);

            //1. Agregar Contacto
            ContactoDTO contactoDTONuevo = contactoService.agregar(contactoDireccionDTO.getContacto());
            ContactoDireccion contactoDireccion = ContactoDireccion.builder()
                    .contacto(contactoMapStruct.toEntity(contactoDTONuevo))
                    .cliente(cliente)
                    .direccion(direccion)
                    .rol(contactoDireccionDTO.getRol())
                    .build();
            //2. Agregar relacion Contacto<-->Direccion
            contactoDireccionMapper.agregar(contactoDireccion);
            logeador.info("Contacto agregado exitosamente a Direccion y Cliente, contacto:{}, cliente: {}, direccion: {}", contactoDTONuevo.getId(),
                    contactoDireccion.getCliente().getId(), contactoDireccion.getDireccion().getId());
            contactoDireccionDTO.setContacto(contactoDTONuevo);
            return contactoDireccionDTO;
        } catch (DuplicateKeyException e) {
            logeador.error(Constantes.DIRECCION_DUPLICADO_MENSAGE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{}", contactoDireccionDTO.getContacto().getNombre(),
                    clienteId, id, DireccionError.DUPLICADO.getCodigoError());
            throw new RecursoDuplicadoException(DireccionError.DUPLICADO.getCodigoError(),
                    Constantes.DIRECCION_DUPLICADO_MENSAGE);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_AGREGAR_MENSAJE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{}", contactoDireccionDTO.getContacto().getNombre(),
                    clienteId, id, GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_AGREGAR_MENSAJE, e);
        }
    }


    /**
     * Actualiza un Contact existente asociado a Direccion
     *
     * @param id                   la clave de Direccion a actualizar.
     * @param clienteId            La clave de Cliente a actualizar.
     * @param contactoId           la clave de Contacto a actualizar.
     * @param contactoDireccionDTO el ContactoDireccion DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Direccion tiene errores.
     */
    @Transactional
    public void actualizarContacto(Long id, Long clienteId, Long contactoId, ContactoDireccionDTO contactoDireccionDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizarContacto()");

        //  Valida Entrada
        if (id == null || id.toString().isEmpty() || clienteId == null || clienteId.toString().isEmpty() || contactoDireccionDTO == null
                || contactoDireccionDTO.getContacto() == null) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": {}, codigoError:{}", ((contactoDireccionDTO.getContacto() != null) ? contactoDireccionDTO.getContacto().toString() : null),
                    DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            Cliente cliente = new Cliente();
            cliente.setId(clienteId);

            Direccion direccion = new Direccion();
            direccion.setId(id);

            //1. Actualiza Contacto
            contactoService.actualizar(contactoId, contactoDireccionDTO.getContacto());

            ContactoDireccion contactoDireccion = ContactoDireccion.builder()
                    .contacto(contactoMapStruct.toEntity(contactoDireccionDTO.getContacto()))
                    .cliente(cliente)
                    .direccion(direccion)
                    .rol(contactoDireccionDTO.getRol())
                    .build();

            //2. Actualiza relacion Contacto<-->Direccion
            contactoDireccionMapper.actualizar(contactoDireccion);

            logeador.info("direccion actualizado exitosamente: contacto:{}, cliente: {}, direccion: {}", contactoDireccionDTO.getContacto().getId(),
                    clienteId, id);
        } catch (DataAccessException | BindingException e) {
            logeador.error(Constantes.DIRECCION_ACTUALIZAR_MENSAJE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{} ", contactoDireccionDTO.getContacto().getId(),
                    clienteId, id, GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_ACTUALIZAR_MENSAJE, e);
        }
    }


    /**
     * Elimina Contacto asociado a Direccion por Clave.
     *
     * @param id         clave de Direccion a eliminar.
     * @param clienteId  La clave de Cliente a eliminar.
     * @param contactoId La clave de Contacto a eliminar.
     * @throws RecursoNoEncontradoException si el Direccion no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Direccion o Contacto esta asociado a otro recurso
     */
    @Transactional
    public void eliminarContacto(Long id, Long clienteId, Long contactoId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminarContacto() direccion contacto: {}, {}, {}}", id, clienteId, contactoId);

        //  Valida Entrada
        if (id == null || id.toString().isEmpty() || clienteId == null || clienteId.toString().isEmpty() || contactoId == null) {
            logeador.error(Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE + ": codigoError:{}",
                    DireccionError.REQUERIDO.getCodigoError());
            throw new EntradaInvalidadException(DireccionError.REQUERIDO.getCodigoError(),
                    Constantes.DIRECCION_ENTRADA_INVALIDA_MENSAGE);
        }


        try {
            Contacto contacto = new Contacto();
            contacto.setId(contactoId);

            Direccion direccion = new Direccion();
            direccion.setId(id);

            Cliente cliente = new Cliente();
            cliente.setId(clienteId);

            ContactoDireccion contactoDireccion = ContactoDireccion.builder()
                    .contacto(contacto)
                    .cliente(cliente)
                    .direccion(direccion).build();
            //1. Elimina relacion Contacto<-->Direccion
            contactoDireccionMapper.eliminar(contactoDireccion);

            //2. Elimina Contacto
            contactoService.eliminar(contactoId);
            logeador.info("contacto / direccion eliminado: contacto:{}, cliente: {}, direccion: {}", contactoId, clienteId, id);
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ELIMINAR_MENSAJE + ": contacto:{}, cliente: {}, direccion: {}, codigoError:{}",
                    contactoId, clienteId, id, GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_ELIMINAR_MENSAJE, e);
        }
    }

    /**
     * Encuentra los Contactos asociados a Direccion en la base de datos por su clave.
     *
     * @param direccionId La clave de Direccion a encontrar.
     * @param clienteId   La clave de Cliente a encontrar.
     * @return lista entidad ContactoDireccion encontrado
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     */
    public ContactoDireccionDTO encontrarDireccionContacto(Long direccionId, Long clienteId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarDireccionContacto(): {}, {}", direccionId, clienteId);

        try {
            ContactoDireccionDTO contactoDireccionDTO = contactoDireccionMapStruct.toDTO(contactoDireccionMapper.encontrarDireccionContacto(direccionId, clienteId));

            if (contactoDireccionDTO != null) {
                logeador.info("direccion encontrado por clave direccion: {}, cliente: {}", direccionId, clienteId);
            } else {
                logeador.info("direccion clave:{}, {} no encontrado codigoError:{}", direccionId, clienteId,
                        DireccionError.NO_ENCONTRADO.getCodigoError());
                throw new RecursoNoEncontradoException(DireccionError.NO_ENCONTRADO.getCodigoError(),
                        Constantes.DIRECCION_NO_ENCONTRADO_MENSAGE);
            }

            return contactoDireccionDTO;
        } catch (DataAccessException e) {
            logeador.error(Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE + " direccion: {}, cliente: {}, codigoError:{} ", direccionId, clienteId,
                    GeneralError.ERROR_INTERNO.getCodigoError(), e);
            throw new BaseDatosException(GeneralError.ERROR_INTERNO.getCodigoError(),
                    Constantes.DIRECCION_ENCONTRAR_POR_CLAVE_MENSAGE, e);
        }
    }

}