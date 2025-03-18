package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ContactoDTO;
import com.elitsoft.servicampo.domain.dto.core.DireccionDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.DireccionMapper;
import com.elitsoft.servicampo.mapstruct.DireccionMapStruct;
import com.elitsoft.servicampo.service.core.DireccionService;
import com.elitsoft.servicampo.service.error.GeneralError;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
     * @param direccionDTO el Direccion DTO.
     * @return el Direccion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    public DireccionDTO agregar(DireccionDTO direccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() direccion");

        return direccionService.agregar(direccionDTO);
    }

    /**
     * Agrega un nuevo Contacto a una Direccion existente.
     * @param  id clave de Direccion a eliminar.
     * @param clienteId La clave de Cliente a eliminar.
     * @param contactoDTO el Contacto DTO.
     * @return el Direccion DTO existente con Contacto DTO agregado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    public ContactoDTO agregarContacto(Long id, Long clienteId, ContactoDTO contactoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarContacto() direccion contacto");

        return direccionService.agregarContacto(id, clienteId, contactoDTO);
    }

    /**
     * Agrega Lote nuevos Direccion.
     * @param direccionDTOLote lista de Direccion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso Direccion ya existe.
     */
    public void agregarLote(List<DireccionDTO> direccionDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() direccion");

        direccionService.agregarLote(direccionDTOLote);
    }

    /**
     * Actualiza un Direccion existente.
     * @param clienteId La clave de Cliente a actualizar.
     * @param id la Clave de Direccion a actualizar.
     * @param direccionDTO el Direccion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizar(Long clienteId, Long id, DireccionDTO direccionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() direccion");

        direccionService.actualizar(clienteId, id, direccionDTO);
    }

    /**
     * Actualiza un Contact existente asociado a Direccion
     * @param clienteId La clave de Cliente a actualizar.
     * @param id la clave de Direccion a actualizar.
     * @param contactoId la clave de Contacto a actualizar.
     * @param contactoDTO el Contacto DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizarContacto(Long clienteId, Long id, Long contactoId, ContactoDTO contactoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() direccion");

        direccionService.actualizarContacto(clienteId, id, contactoId, contactoDTO);
    }

    /**
     * Actualiza Lote de Direccion existentes.
     * @param direccionDTOLote lista de Direccion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Direccion tiene errores.
     */
    public void actualizarLote(List<DireccionDTO> direccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() direccion");

        direccionService.actualizarLote(direccionDTOLote);
    }

    /**
     * Elimina Direccion por Clave.
     * @param clienteId La clave de Cliente a eliminar.
     * @param  id clave de Direccion a eliminar.
     * @throws RecursoNoEncontradoException si el Direccion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id, Long clienteId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() direccion: {}", id);
        direccionService.eliminar(clienteId, id);
    }


    /**
     * Elimina Contacto asociado a Direccion por Clave.
     * @param  id clave de Direccion a eliminar.
     * @param clienteId La clave de Cliente a eliminar.
     * @param contactoId La clave de Contacto a eliminar.
     * @throws RecursoNoEncontradoException si el Direccion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarContacto(Long id, Long clienteId, Long contactoId) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminarContacto() direccion: {}", id);
        direccionService.eliminarContacto(clienteId, id, contactoId);
    }

    /**
     * Elimina Lote Direccion por Clave.
     * @param direccionDTOLote lista de claves de Direccion a eliminar.
     * @throws EntradaInvalidadException si la lista  Direccion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<DireccionDTO> direccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        direccionService.eliminarLote(direccionDTOLote);
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
        logeador.debug("encontrarPorClave(): {}, {}", clientId, id);
        return direccionService.encontrarPorClave(clientId, id);
    }

    /**
     * Encuentra un Direccion en la base de datos por su clave con Lista de Contacto
     * @param clientId La clave de Cliente a encontrar.
     * @param id La clave de Direccion a encontrar.
     * @return el Direccion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Direccion no es encontrado.
     */
    public DireccionDTO encontrarPorClaveConContactos(Long clientId, Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClaveConContactos(): {}, {}", clientId, id);
        return direccionService.encontrarPorClaveConContactos(clientId, id);
    }

    /**
     * Obtiene todos los Direccion desde la base de datos filtrado por Cliente
     * @param clientId clave Cliente a filtrar
     * @return una lista de todos Direccion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DireccionDTO> obtenerTodosPorCliente(Long clientId) throws BaseDatosException {
        logeador.debug("obtenerTodosPorCliente() {}",clientId);

        return direccionService.obtenerTodosPorCliente(clientId);
    }

    /**
     * Obtiene todos los Direccion desde la base de datos filtrado por Cliente con Lista de Contacto
     * @param clientId clave Cliente a filtrar
     * @return una lista de todos Direccion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DireccionDTO> obtenerTodosPorClienteConContactos(Long clientId) throws BaseDatosException {
        logeador.debug("obtenerTodosPorClienteConContactos()");

        return direccionService.obtenerTodosPorClienteConContactos(clientId);
    }

}