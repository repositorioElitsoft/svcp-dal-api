package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.SegmentacionClienteDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.SegmentacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.SegmentacionClienteMapStruct;
import com.elitsoft.servicampo.service.core.SegmentacionClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  SegmentacionCliente.
 */
@Component
public class SegmentacionClienteMobileService {

    @Autowired
    private SegmentacionClienteMapper segmentacionClienteMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private SegmentacionClienteService segmentacionClienteService; //Logica de Negocio del Core Service

    @Autowired
    private SegmentacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(SegmentacionClienteMobileService.class); //Logback

    /**
     * Agrega un nuevo SegmentacionCliente.
     * @param segmentacionClienteDto el SegmentacionCliente DTO.
     * @return el SegmentacionCliente DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso SegmentacionCliente ya existe.
     */
    public SegmentacionClienteDto agregar(SegmentacionClienteDto segmentacionClienteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() segmentacioncliente");

        return segmentacionClienteService.agregar(segmentacionClienteDto);
    }

    /**
     * Agrega Lote nuevos SegmentacionCliente.
     * @param segmentacionclienteLoteDto lista de SegmentacionCliente DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     * @throws RecursoDuplicadoException si el recurso SegmentacionCliente ya existe.
     */
    public void agregarLote(List<SegmentacionClienteDto> segmentacionclienteLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() segmentacioncliente");

        segmentacionClienteService.agregarLote(segmentacionclienteLoteDto);
    }

    /**
     * Actualiza un SegmentacionCliente existente.
     * @param id la Clave de SegmentacionCliente a actualizar.
     * @param segmentacionClienteDto el SegmentacionCliente DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si SegmentacionCliente no es encontrado.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     */
    public void actualizar(Long id, SegmentacionClienteDto segmentacionClienteDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() segmentacioncliente");

        segmentacionClienteService.actualizar(id, segmentacionClienteDto);
    }

    /**
     * Actualiza Lote de SegmentacionCliente existentes.
     * @param segmentacionClienteLoteDto lista de SegmentacionCliente DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada SegmentacionCliente tiene errores.
     */
    public void actualizarLote(List<SegmentacionClienteDto> segmentacionClienteLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() segmentacioncliente");

        segmentacionClienteService.actualizarLote(segmentacionClienteLoteDto);
    }

    /**
     * Elimina SegmentacionCliente por Clave.
     * @param id la clave de SegmentacionCliente a eliminar.
     * @throws RecursoNoEncontradoException si el SegmentacionCliente no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() segmentacioncliente: {}", id);
        segmentacionClienteService.eliminar(id);
    }

    /**
     * Elimina Lote SegmentacionCliente por Clave.
     * @param idLote lista de claves de SegmentacionCliente a eliminar.
     * @throws EntradaInvalidadException si la lista  SegmentacionCliente esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        segmentacionClienteService.eliminarLote(idLote);
    }

    /**
     * Encuentra un SegmentacionCliente por Clave.
     * @param id la clave SegmentacionCliente a encontrar.
     * @return el SegmentacionCliente DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si SegmentacionCliente no es encontrado.
     */
    public SegmentacionClienteDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return segmentacionClienteService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los SegmentacionClientes.
     * @return lista de todos SegmentacionCliente DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<SegmentacionClienteDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return segmentacionClienteService.obtenerTodos();
    }
}