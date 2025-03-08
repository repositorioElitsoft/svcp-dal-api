package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TareaDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TareaMapper;
import com.elitsoft.servicampo.mapstruct.TareaMapStruct;
import com.elitsoft.servicampo.service.core.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Tarea.
 */
@Component
public class TareaMobileService {

    @Autowired
    private TareaMapper tareaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TareaService tareaService; //Logica de Negocio del Core Service

    @Autowired
    private TareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TareaMobileService.class); //Logback


    /**
     * Agrega un nuevo Tarea.
     * @param tareaDTO el Tarea DTO.
     * @return el Tarea DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Tarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso Tarea ya existe.
     */
    public TareaDTO agregar(TareaDTO tareaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tarea");

        return tareaService.agregar(tareaDTO);
    }

    /**
     * Agrega Lote nuevos Tarea.
     * @param tareaLoteDTO lista de Tarea DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Tarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso Tarea ya existe.
     */
    public void agregarLote(List<TareaDTO> tareaLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tarea");

        tareaService.agregarLote(tareaLoteDTO);
    }

    /**
     * Actualiza un Tarea existente.
     * @param id la Clave de Tarea a actualizar.
     * @param tareaDTO el Tarea DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Tarea no es encontrado.
     * @throws EntradaInvalidadException si la entrada Tarea tiene errores.
     */
    public void actualizar(Long id, TareaDTO tareaDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tarea");

        tareaService.actualizar(id, tareaDTO);
    }

    /**
     * Actualiza Lote de Tarea existentes.
     * @param tareaLoteDTO lista de Tarea DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Tarea tiene errores.
     */
    public void actualizarLote(List<TareaDTO> tareaLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tarea");

        tareaService.actualizarLote(tareaLoteDTO);
    }

    /**
     * Elimina Tarea por Clave.
     * @param id la clave de Tarea a eliminar.
     * @throws RecursoNoEncontradoException si el Tarea no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tarea: {}", id);
        tareaService.eliminar(id);
    }

    /**
     * Elimina Lote Tarea por Clave.
     * @param idLote lista de claves de Tarea a eliminar.
     * @throws EntradaInvalidadException si la lista  Tarea esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tareaService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Tarea por Clave.
     * @param id la clave Tarea a encontrar.
     * @return el Tarea DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Tarea no es encontrado.
     */
    public TareaDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tareaService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Tareas.
     * @return lista de todos Tarea DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TareaDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tareaService.obtenerTodos();
    }
}