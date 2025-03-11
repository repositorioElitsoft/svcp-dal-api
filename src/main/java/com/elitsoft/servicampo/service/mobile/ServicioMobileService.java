package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.ServicioDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.ServicioMapper;
import com.elitsoft.servicampo.mapstruct.ServicioMapStruct;
import com.elitsoft.servicampo.service.core.ServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Servicio.
 */
@Component
public class ServicioMobileService {

    @Autowired
    private ServicioMapper servicioMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ServicioService servicioService; //Logica de Negocio del Core Service

    @Autowired
    private ServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(ServicioMobileService.class); //Logback


    /**
     * Agrega un nuevo Servicio.
     * @param servicioDTO el Servicio DTO.
     * @return el Servicio DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso Servicio ya existe.
     */
    public ServicioDTO agregar(ServicioDTO servicioDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() servicio");

        return servicioService.agregar(servicioDTO);
    }

    /**
     * Agrega Lote nuevos Servicio.
     * @param servicioDTOLote lista de Servicio DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso Servicio ya existe.
     */
    public void agregarLote(List<ServicioDTO> servicioDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() servicio");

        servicioService.agregarLote(servicioDTOLote);
    }

    /**
     * Actualiza un Servicio existente.
     * @param id la Clave de Servicio a actualizar.
     * @param servicioDTO el Servicio DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Servicio no es encontrado.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     */
    public void actualizar(Long id, ServicioDTO servicioDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() servicio");

        servicioService.actualizar(id, servicioDTO);
    }

    /**
     * Actualiza Lote de Servicio existentes.
     * @param servicioDTOLote lista de Servicio DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Servicio tiene errores.
     */
    public void actualizarLote(List<ServicioDTO> servicioDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() servicio");

        servicioService.actualizarLote(servicioDTOLote);
    }

    /**
     * Elimina Servicio por Clave.
     * @param id la clave de Servicio a eliminar.
     * @throws RecursoNoEncontradoException si el Servicio no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() servicio: {}", id);
        servicioService.eliminar(id);
    }

    /**
     * Elimina Lote Servicio por Clave.
     * @param servicioDTOLote lista de claves de Direccion a eliminar.
     * @throws EntradaInvalidadException si la lista  Servicio esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<ServicioDTO> servicioDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        servicioService.eliminarLote(servicioDTOLote);
    }

    /**
     * Encuentra un Servicio por Clave.
     * @param id la clave Servicio a encontrar.
     * @return el Servicio DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Servicio no es encontrado.
     */
    public ServicioDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return servicioService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Servicios.
     * @return lista de todos Servicio DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<ServicioDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return servicioService.obtenerTodos();
    }
}