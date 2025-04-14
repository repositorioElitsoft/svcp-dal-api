package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.domain.dto.core.ContactoDireccionDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ContactoMapper;
import com.elitsoft.servicampo.mapstruct.ContactoMapStruct;
import com.elitsoft.servicampo.service.core.ContactoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Contacto.
 */
@Component
public class ContactoMobileService {

    @Autowired
    private ContactoMapper contactoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ContactoService contactoService; //Logica de Negocio del Core Service

    @Autowired
    private ContactoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ContactoMobileService.class); //Logback


    /**
     * Agrega un nuevo Contacto.
     * @param contactoDTO el Contacto DTO.
     * @return el Contacto DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Contacto ya existe.
     */
    public ContactoDTO agregar(ContactoDTO contactoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() contacto");

        return contactoService.agregar(contactoDTO);
    }

    /**
     * Agrega Lote nuevos Contacto.
     * @param contactoDTOLote lista de Contacto DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     * @throws RecursoDuplicadoException si el recurso Contacto ya existe.
     */
    public void agregarLote(List<ContactoDTO> contactoDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() contacto");

        contactoService.agregarLote(contactoDTOLote);
    }

    /**
     * Actualiza un Contacto existente.
     * @param id la Clave de Contacto a actualizar.
     * @param contactoDTO el Contacto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contacto no es encontrado.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     */
    public void actualizar(Long id, ContactoDTO contactoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() contacto");

        contactoService.actualizar(id, contactoDTO);
    }

    /**
     * Actualiza Lote de Contacto existentes.
     * @param contactoDTOLote lista de Contacto DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Contacto tiene errores.
     */
    public void actualizarLote(List<ContactoDTO> contactoDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() contacto");

        contactoService.actualizarLote(contactoDTOLote);
    }

    /**
     * Elimina Contacto por Clave.
     * @param id la clave de Contacto a eliminar.
     * @throws RecursoNoEncontradoException si el Contacto no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Contacto o DocumentoIdentificacion esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() contacto: {}", id);
        contactoService.eliminar(id);
    }

    /**
     * Elimina Lote Contacto por Clave.
     * @param idLote lista de claves de Contacto a eliminar.
     * @throws EntradaInvalidadException si la lista  Contacto esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        contactoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Contacto por Clave.
     * @param id la clave Contacto a encontrar.
     * @return el Contacto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contacto no es encontrado.
     */
    public ContactoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return contactoService.encontrarPorClave(id);
    }

    /**
     * Obtiene Lista de Direccion de un Contacto
     * @param clienteId La clave de Cliente a encontrar.
     * @param id la clave Contacto a encontrar.
     * @return el Contacto DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Contacto no es encontrado.
     */
    public List<ContactoDireccionDTO> obtenerDireccionesPorContacto(Long clienteId, Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("obtenerDireccionesPorContacto(): {}, {}", clienteId, id);
        return contactoService.obtenerDireccionesPorContacto(clienteId, id);
    }

    /**
     * Obtiene todos los Contactos.
     * @return lista de todos Contacto DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ContactoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return contactoService.obtenerTodos();
    }
}