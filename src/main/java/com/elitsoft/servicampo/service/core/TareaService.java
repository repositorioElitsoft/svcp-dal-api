package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TareaDto;
import com.elitsoft.servicampo.domain.entity.Tarea;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TareaMapper;
import com.elitsoft.servicampo.mapstruct.TareaMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Tarea.
 */
@Service
@Transactional
public class TareaService {

    @Autowired
    private TareaMapper tareaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TareaService.class);

    /**
     * Agrega un nuevo Tarea.
     * @param tareaDto El Tarea DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TareaDto tareaDto) throws BaseDatosException {
        logeador.debug("agregar() tarea");


        try {
            Tarea tarea = mapper.toEntity(tareaDto);
            Long nuevoId = tareaMapper.agregar(tarea);
            logeador.info("Tarea agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_AGREGAR_EXECPTION + ": {}", tareaDto.toString(), e);
            throw new BaseDatosException(Constantes.TAREA_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Tarea existente.
     * @param id La Clave de Tarea a actualizar.
     * @param tareaDto El Tarea DTO con informacion actualizada.
     * @throws TareaNoEncontradoException Si Tarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TareaDto tareaDto) throws TareaNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() tarea");

        try {
            TareaDto tareaDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Tarea tarea = mapper.toEntity(tareaDto);
            tarea.setId(id);
            int registrosActualizados = tareaMapper.actualizar(tarea);
            logeador.info("tarea actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (TareaNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_ACTUALIZAR_EXECPTION + ": id={} {}", id, tareaDto.toString(), e);
            throw new BaseDatosException(Constantes.TAREA_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Tarea por Clave.
     * @param id La Clave de Tarea a eliminar.
     * @throws TareaNoEncontradoException Si el Tarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws TareaNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tarea: {}", id);

        try {
            TareaDto tareaDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tareaMapper.eliminar(id);
            logeador.info("tarea eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (TareaNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.TAREA_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Tarea por Clave.
     * @param id La Clave Tarea a encontrar.
     * @return El Tarea DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TareaNoEncontradoException Si Tarea no es encontrado.
     */
    public TareaDto encontrarPorClave(Long id) throws BaseDatosException, TareaNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TareaDto tareaDto = mapper.toDto(tareaMapper.encontrarPorClave(id));

            if (tareaDto != null) {
                logeador.info("tarea encontrado por clave : {}", id);
            } else {
                logeador.info("tarea clave:{} no encontrado", id);
                throw new TareaNoEncontradoException(Constantes.TAREA_NO_ENCONTRADO_MENSAGE);
            }

            return tareaDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.TAREA_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Tareas.
     * @return Una lista de todos Tarea DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TareaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TareaDto> tareaList = mapper.toDtoList(tareaMapper.obtenerTodos());
            logeador.info("tareas obtenidos");
            return tareaList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TAREA_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.TAREA_OBTENER_TODOS_EXECPTION, e);
        }
    }
}