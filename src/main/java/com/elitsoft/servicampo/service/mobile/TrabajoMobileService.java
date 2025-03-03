package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TrabajoMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoMapStruct;
import com.elitsoft.servicampo.service.core.TrabajoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Trabajo.
 */
@Component
public class TrabajoMobileService {

    @Autowired
    private TrabajoMapper trabajoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoService trabajoService; //Logica de Negocio del Core Service

    @Autowired
    private TrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoMobileService.class); //Logback

    /**
     * Agrega un nuevo Trabajo.
     * @param trabajoDto el Trabajo DTO.
     * @return el Trabajo DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso Trabajo ya existe.
     */
    public TrabajoDto agregar(TrabajoDto trabajoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() trabajo");

        return trabajoService.agregar(trabajoDto);
    }

    /**
     * Agrega Lote nuevos Trabajo.
     * @param trabajoLoteDto lista de Trabajo DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     * @throws RecursoDuplicadoException si el recurso Trabajo ya existe.
     */
    public void agregarLote(List<TrabajoDto> trabajoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() trabajo");

        trabajoService.agregarLote(trabajoLoteDto);
    }

    /**
     * Actualiza un Trabajo existente.
     * @param id la Clave de Trabajo a actualizar.
     * @param trabajoDto el Trabajo DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Trabajo no es encontrado.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     */
    public void actualizar(Long id, TrabajoDto trabajoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() trabajo");

        trabajoService.actualizar(id, trabajoDto);
    }

    /**
     * Actualiza Lote de Trabajo existentes.
     * @param trabajoLoteDto lista de Trabajo DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Trabajo tiene errores.
     */
    public void actualizarLote(List<TrabajoDto> trabajoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() trabajo");

        trabajoService.actualizarLote(trabajoLoteDto);
    }

    /**
     * Elimina Trabajo por Clave.
     * @param id la clave de Trabajo a eliminar.
     * @throws RecursoNoEncontradoException si el Trabajo no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() trabajo: {}", id);
        trabajoService.eliminar(id);
    }

    /**
     * Elimina Lote Trabajo por Clave.
     * @param idLote lista de claves de Trabajo a eliminar.
     * @throws EntradaInvalidadException si la lista  Trabajo esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        trabajoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Trabajo por Clave.
     * @param id la clave Trabajo a encontrar.
     * @return el Trabajo DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Trabajo no es encontrado.
     */
    public TrabajoDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return trabajoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Trabajos.
     * @return lista de todos Trabajo DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TrabajoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return trabajoService.obtenerTodos();
    }
}