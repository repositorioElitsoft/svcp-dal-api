package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ClasificacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClasificacionClienteMapStruct;
import com.elitsoft.servicampo.service.core.ClasificacionClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  ClasificacionCliente.
 */
@Component
public class ClasificacionClienteMobileService {

    @Autowired
    private ClasificacionClienteMapper clasificacionclienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClasificacionClienteService clasificacionclienteService; //Logica de Negocio del Core Service

    @Autowired
    private ClasificacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteMobileService.class); //Logback

    /**
     * Agrega un nuevo ClasificacionCliente.
     *
     * @param clasificacionClienteDTO el ClasificacionCliente DTO.
     * @return el ClasificacionCliente DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ClasificacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso ClasificacionCliente ya existe.
     */
    public ClasificacionClienteDTO agregar(ClasificacionClienteDTO clasificacionClienteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() clasificacioncliente");

        return clasificacionclienteService.agregar(clasificacionClienteDTO);
    }

    /**
     * Agrega Lote nuevos ClasificacionCliente.
     *
     * @param clasificacionClienteLoteDTO lista de ClasificacionCliente DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ClasificacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso ClasificacionCliente ya existe.
     */
    public void agregarLote(List<ClasificacionClienteDTO> clasificacionClienteLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() clasificacioncliente");

        clasificacionclienteService.agregarLote(clasificacionClienteLoteDTO);
    }

    /**
     * Actualiza un ClasificacionCliente existente.
     *
     * @param id                      la Clave de ClasificacionCliente a actualizar.
     * @param clasificacionClienteDTO el ClasificacionCliente DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ClasificacionCliente no es encontrado.
     * @throws EntradaInvalidadException    si la entrada ClasificacionCliente tiene errores.
     */
    public void actualizar(Long id, ClasificacionClienteDTO clasificacionClienteDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() clasificacioncliente");

        clasificacionclienteService.actualizar(id, clasificacionClienteDTO);
    }

    /**
     * Actualiza Lote de ClasificacionCliente existentes.
     *
     * @param clasificacionClienteLoteDTO lista de ClasificacionCliente DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada ClasificacionCliente tiene errores.
     */
    public void actualizarLote(List<ClasificacionClienteDTO> clasificacionClienteLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() clasificacioncliente");

        clasificacionclienteService.actualizarLote(clasificacionClienteLoteDTO);
    }

    /**
     * Elimina ClasificacionCliente por Clave.
     *
     * @param id la clave de ClasificacionCliente a eliminar.
     * @throws RecursoNoEncontradoException si el ClasificacionCliente no es encontrado.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoEliminarException     si ClasificacionCliente esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() clasificacioncliente: {}", id);
        clasificacionclienteService.eliminar(id);
    }

    /**
     * Elimina Lote ClasificacionCliente por Clave.
     *
     * @param idLote lista de claves de ClasificacionCliente a eliminar.
     * @throws EntradaInvalidadException si la lista  ClasificacionCliente esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si ClasificacionCliente esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");
        clasificacionclienteService.eliminarLote(idLote);
    }

    /**
     * Encuentra un ClasificacionCliente por Clave.
     *
     * @param id la clave ClasificacionCliente a encontrar.
     * @return el ClasificacionCliente DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si ClasificacionCliente no es encontrado.
     */
    public ClasificacionClienteDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return clasificacionclienteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los ClasificacionClientes.
     *
     * @return lista de todos ClasificacionCliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ClasificacionClienteDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return clasificacionclienteService.obtenerTodos();
    }
}