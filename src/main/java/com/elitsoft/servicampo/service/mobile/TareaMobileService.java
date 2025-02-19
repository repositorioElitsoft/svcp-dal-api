package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TareaDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.TareaNoEncontradoException;
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
    private TareaMapper tareaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TareaService tareaService; //Logica de Negocio del Core Service

    @Autowired
    private TareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TareaMobileService.class);

    /**
     * Agrega un nuevo Tarea.
     * @param tareaDto El Tarea DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TareaDto tareaDto) throws BaseDatosException {
        logeador.debug("agregar() tarea");
        tareaService.agregar(tareaDto);
    }

    /**
     * Actualiza un Tarea existente.
     * @param id La Clave de Tarea a actualizar.
     * @param tareaDto El Tarea DTO con informacion actualizada.
     * @throws TareaNoEncontradoException Si Tarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TareaDto tareaDto) throws BaseDatosException, TareaNoEncontradoException {
        logeador.debug("actualizar() tarea");
        tareaService.actualizar(id, tareaDto);
    }

    /**
     * Elimina Tarea por Clave.
     * @param id La Clave de Tarea a eliminar.
     * @throws TareaNoEncontradoException Si el Tarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, TareaNoEncontradoException {
        logeador.debug("eliminar() tarea: {}", id);
        tareaService.eliminar(id);
    }

    /**
     * Encuentra un Tarea por Clave.
     * @param id La Clave Tarea a encontrar.
     * @return El Tarea DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TareaNoEncontradoException Si Tarea no es encontrado.
     */
    public TareaDto encontrarPorClave(Long id) throws BaseDatosException, TareaNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tareaService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Tareas.
     * @return Una lista de todos Tarea DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TareaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tareaService.obtenerTodos();
    }
}