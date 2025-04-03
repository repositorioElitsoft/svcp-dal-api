package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ClienteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClienteMapStruct;
import com.elitsoft.servicampo.service.core.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Cliente.
 */
@Component
public class ClienteMobileService {

    @Autowired
    private ClienteMapper clienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClienteService clienteService; //Logica de Negocio del Core Service

    @Autowired
    private ClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ClienteMobileService.class); //Logback


    /**
     * Agrega un nuevo Cliente.
     * @param clienteDTO el Cliente DTO.
     * @return el Cliente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso Cliente ya existe.
     */
    public ClienteDTO agregar(ClienteDTO clienteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() cliente");

        return clienteService.agregar(clienteDTO);
    }

    /**
     * Agrega Lote nuevos Cliente.
     * @param clienteDTOLote lista de Cliente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso Cliente ya existe.
     */
    public void agregarLote(List<ClienteDTO> clienteDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() cliente");

        clienteService.agregarLote(clienteDTOLote);
    }

    /**
     * Actualiza un Cliente existente.
     * @param id la Clave de Cliente a actualizar.
     * @param clienteDTO el Cliente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Cliente no es encontrado.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     */
    public void actualizar(Long id, ClienteDTO clienteDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() cliente");

        clienteService.actualizar(id, clienteDTO);
    }

    /**
     * Actualiza Lote de Cliente existentes.
     * @param clienteDTOLote lista de Cliente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Cliente tiene errores.
     */
    public void actualizarLote(List<ClienteDTO> clienteDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() cliente");

        clienteService.actualizarLote(clienteDTOLote);
    }

    /**
     * Elimina Cliente por Clave.
     * @param id la clave de Cliente a eliminar.
     * @throws RecursoNoEncontradoException si el Cliente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si Cliente o DocumentoIdentificacion esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() cliente: {}", id);
        clienteService.eliminar(id);
    }

    /**
     * Elimina Lote Cliente por Clave.
     * @param idLote lista de claves de Cliente a eliminar.
     * @throws EntradaInvalidadException si la lista  Cliente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        clienteService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Cliente por Clave.
     * @param id la clave Cliente a encontrar.
     * @return el Cliente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Cliente no es encontrado.
     */
    public ClienteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return clienteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Clientes.
     * @return lista de todos Cliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ClienteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return clienteService.obtenerTodos();
    }
}