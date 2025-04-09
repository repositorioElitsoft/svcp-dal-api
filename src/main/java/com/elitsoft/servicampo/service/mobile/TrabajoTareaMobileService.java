package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TrabajoTareaDTO;
import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
import com.elitsoft.servicampo.mapper.TrabajoTareaMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoTareaMapStruct;
import com.elitsoft.servicampo.service.core.TrabajoTareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TrabajoTarea.
 */
@Component
public class TrabajoTareaMobileService {

    @Autowired
    private TrabajoTareaMapper trabajoTareaMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoTareaService trabajoTareaService; //Logica de Negocio del Core Service

    @Autowired
    private TrabajoTareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoTareaMobileService.class); //Logback

    /**
     * Agrega un nuevo TrabajoTarea.
     * @param trabajoTareaDTO el TrabajoTarea DTO.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TrabajoTarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso TrabajoTarea ya existe.
     */
    public void agregar(TrabajoTareaDTO trabajoTareaDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() trabajotarea");

        trabajoTareaService.agregar(trabajoTareaDTO);
    }


    /**
     * Agrega Lote nuevos TrabajoTarea.
     * @param trabajoTareaLoteDTO lista de TrabajoTarea DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TrabajoTarea tiene errores.
     * @throws RecursoDuplicadoException si el recurso TrabajoTarea ya existe.
     */
    public void agregarLote(List<TrabajoTareaDTO> trabajoTareaLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() trabajotarea");

        trabajoTareaService.agregarLote(trabajoTareaLoteDTO);
    }

    /**
     * Actualiza un TrabajoTarea existente.
     * @param trabajoId la Clave de Trabajo a actualizar.
     * @param tareaId la Clave de Tarea a actualizar.
     * @param trabajoTareaDTO el TrabajoTarea DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TrabajoTarea no es encontrado.
     * @throws EntradaInvalidadException si la entrada TrabajoTarea tiene errores.
     */
    public void actualizar(Long trabajoId, Long tareaId, TrabajoTareaDTO trabajoTareaDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() trabajotarea");

        trabajoTareaService.actualizar(trabajoId, tareaId, trabajoTareaDTO);
    }

    /**
     * Actualiza Lote de TrabajoTarea existentes.
     * @param trabajoTareaLoteDTO lista de TrabajoTarea DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TrabajoTarea tiene errores.
     */
    public void actualizarLote(List<TrabajoTareaDTO> trabajoTareaLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() trabajotarea");

        trabajoTareaService.actualizarLote(trabajoTareaLoteDTO);
    }

    /**
     * Elimina TrabajoTarea por Clave.
     * @param trabajoId la clave de Trabajo a eliminar.
     * @param tareaId la clave de Tarea a eliminar.
     * @throws RecursoNoEncontradoException si el TrabajoTarea no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TrabajoTarea esta asociado a otro recurso
     */
    public void eliminar(Long trabajoId, Long tareaId) throws RecursoNoEncontradoException, BaseDatosException, RecursoEliminarException {
        logeador.debug("eliminar() trabajotarea: {}, {}", trabajoId, tareaId);
        trabajoTareaService.eliminar(trabajoId, tareaId);
    }

    /**
     * Elimina Lote TrabajoTarea por Clave.
     * @param trabajoTareaLote lista de  TrabajoTarea a eliminar.
     * @throws EntradaInvalidadException si la lista  TrabajoTarea esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TrabajoTarea esta asociado a otro recurso
     */
    public void eliminarLote(List<TrabajoTarea> trabajoTareaLote) throws  BaseDatosException, EntradaInvalidadException, RecursoEliminarException {
        logeador.debug("eliminarLote()");

        trabajoTareaService.eliminarLote(trabajoTareaLote);
    }

    /**
     * Encuentra un TrabajoTarea por Clave.
     * @param trabajoId la clave TrabajoTarea a encontrar.
     * @param tareaId la clave TrabajoTarea a encontrar.
     * @return el TrabajoTarea DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TrabajoTarea no es encontrado.
     */
    public TrabajoTareaDTO encontrarPorClave(Long trabajoId, Long tareaId) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}, {}", trabajoId, tareaId);
        return trabajoTareaService.encontrarPorClave(trabajoId,tareaId );
    }

    /**
     * Obtiene todos los TrabajoTareas.
     * @return lista de todos TrabajoTarea DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TrabajoTareaDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return trabajoTareaService.obtenerTodos();
    }
}