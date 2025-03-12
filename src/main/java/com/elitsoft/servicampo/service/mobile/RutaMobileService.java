package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.RutaDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.RutaMapper;
import com.elitsoft.servicampo.mapstruct.RutaMapStruct;
import com.elitsoft.servicampo.service.core.RutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Ruta.
 */
@Component
public class RutaMobileService {

    @Autowired
    private RutaMapper rutaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private RutaService rutaService; //Logica de Negocio del Core Service

    @Autowired
    private RutaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(RutaMobileService.class); //Logback


    /**
     * Agrega un nuevo Ruta.
     * @param rutaDTO el Ruta DTO.
     * @return el Ruta DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     * @throws RecursoDuplicadoException si el recurso Ruta ya existe.
     */
    public RutaDTO agregar(RutaDTO rutaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() ruta");

        return rutaService.agregar(rutaDTO);
    }

    /**
     * Agrega Lote nuevos Ruta.
     * @param rutaDTOLote lista de Ruta DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     * @throws RecursoDuplicadoException si el recurso Ruta ya existe.
     */
    public void agregarLote(List<RutaDTO> rutaDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() ruta");

        rutaService.agregarLote(rutaDTOLote);
    }

    /**
     * Actualiza un Ruta existente.
     * @param id la Clave de Ruta a actualizar.
     * @param rutaDTO el Ruta DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Ruta no es encontrado.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     */
    public void actualizar(Long id, RutaDTO rutaDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() ruta");

        rutaService.actualizar(id, rutaDTO);
    }

    /**
     * Actualiza Lote de Ruta existentes.
     * @param rutaDTOLote lista de Ruta DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Ruta tiene errores.
     */
    public void actualizarLote(List<RutaDTO> rutaDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() ruta");

        rutaService.actualizarLote(rutaDTOLote);
    }

    /**
     * Elimina Ruta por Clave.
     * @param id la clave de Ruta a eliminar.
     * @throws RecursoNoEncontradoException si el Ruta no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() ruta: {}", id);
        rutaService.eliminar(id);
    }

    /**
     * Elimina Lote Ruta por Clave.
     * @param rutaDTOLote lista de claves de Ruta a eliminar.
     * @throws EntradaInvalidadException si la lista  Ruta esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<RutaDTO> rutaDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        rutaService.eliminarLote(rutaDTOLote);
    }

    /**
     * Encuentra un Ruta por Clave.
     * @param id la clave Ruta a encontrar.
     * @return el Ruta DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Ruta no es encontrado.
     */
    public RutaDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return rutaService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Rutas.
     * @return lista de todos Ruta DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<RutaDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return rutaService.obtenerTodos();
    }
}