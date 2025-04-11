package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContactoDireccionDTO;
import com.elitsoft.servicampo.domain.dto.core.DireccionDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.DireccionMapper;
import com.elitsoft.servicampo.mapstruct.DireccionMapStruct;
import com.elitsoft.servicampo.service.core.DireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Direccion.
 */
@Component
public class DireccionMobileService {

    @Autowired
    private DireccionMapper direccionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DireccionService direccionService; //Logica de Negocio del Core Service

    @Autowired
    private DireccionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(DireccionMobileService.class); //Logback


    /**
     * Agrega un nuevo Direccion.
     *
     * @param direccionDTO el Direccion DTO.
     * @return el Direccion DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    public DireccionDTO agregar(DireccionDTO direccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() direccion");

        return direccionService.agregar(direccionDTO);
    }

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
    public ContactoDireccionDTO agregarContacto(Long id, Long clienteId, ContactoDireccionDTO contactoDireccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregarContacto() direccion contacto");

        return direccionService.agregarContacto(id, clienteId, contactoDireccionDTO);
    }

    /**
     * Agrega Lote nuevos Direccion.
     *
     * @param direccionDTOLote lista de Direccion DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    public void agregarLote(List<DireccionDTO> direccionDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() direccion");

        direccionService.agregarLote(direccionDTOLote);
    }

    /**
     * Actualiza un Direccion existente.
     *
     * @param clienteId    La clave de Cliente a actualizar.
     * @param id           la Clave de Direccion a actualizar.
     * @param direccionDTO el Direccion DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     * @throws EntradaInvalidadException    si la entrada Direccion tiene errores.
     */
    public void actualizar(Long clienteId, Long id, DireccionDTO direccionDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() direccion");

        direccionService.actualizar(clienteId, id, direccionDTO);
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
    public void actualizarContacto(Long id, Long clienteId, Long contactoId, ContactoDireccionDTO contactoDireccionDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() direccion");

        direccionService.actualizarContacto(clienteId, id, contactoId, contactoDireccionDTO);
    }

    /**
     * Actualiza Lote de Direccion existentes.
     *
     * @param direccionDTOLote lista de Direccion DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizarLote(List<DireccionDTO> direccionDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() direccion");

        direccionService.actualizarLote(direccionDTOLote);
    }

    /**
     * Elimina Direccion por Clave.
     *
     * @param clienteId La clave de Cliente a eliminar.
     * @param id        clave de Direccion a eliminar.
     * @throws RecursoNoEncontradoException si el Direccion no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     */
    public void eliminar(Long id, Long clienteId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() direccion: {}", id);
        direccionService.eliminar(clienteId, id);
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
    public void eliminarContacto(Long id, Long clienteId, Long contactoId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminarContacto() direccion: {}", id);
        direccionService.eliminarContacto(clienteId, id, contactoId);
    }

    /**
     * Elimina Lote Direccion por Clave.
     *
     * @param direccionDTOLote lista de claves de Direccion a eliminar.
     * @throws EntradaInvalidadException si la lista  Direccion esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     */
    public void eliminarLote(List<DireccionDTO> direccionDTOLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        direccionService.eliminarLote(direccionDTOLote);
    }

    /**
     * Encuentra un Direccion por Clave.
     *
     * @param clientId La clave de Cliente a encontrar.
     * @param id       La clave de Direccion a encontrar.
     * @return el Direccion DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     */
    public DireccionDTO encontrarPorClave(Long clientId, Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}, {}", clientId, id);
        return direccionService.encontrarPorClave(clientId, id);
    }

    /**
     * Encuentra un Direccion en la base de datos por su clave con Lista de Contacto
     *
     * @param clientId La clave de Cliente a encontrar.
     * @param id       La clave de Direccion a encontrar.
     * @return el Direccion DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     */
    public List<ContactoDireccionDTO> encontrarContactos(Long clientId, Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarContactos(): {}, {}", clientId, id);
        return direccionService.encontrarContactos(clientId, id);
    }


}