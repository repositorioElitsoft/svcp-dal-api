package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TrabajoTareaDto;
import com.elitsoft.servicampo.domain.entity.TrabajoTarea;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TrabajoTareaMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoTareaMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad TrabajoTarea.
 */
@Service
@Transactional
public class TrabajoTareaService {

    @Autowired
    private TrabajoTareaMapper trabajotareaMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoTareaMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoTareaService.class);

    /**
     * Agrega un nuevo TrabajoTarea.
     * @param trabajotareaDto El TrabajoTarea DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TrabajoTareaDto trabajotareaDto) throws BaseDatosException {
        logeador.debug("agregar() trabajotarea");


        try {
            TrabajoTarea trabajotarea = mapper.toEntity(trabajotareaDto);
            Long nuevoId = trabajotareaMapper.agregar(trabajotarea);
            logeador.info("TrabajoTarea agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_AGREGAR_EXECPTION + ": {}", trabajotareaDto.toString(), e);
            throw new BaseDatosException(Constantes.TRABAJOTAREA_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un TrabajoTarea existente.
     * @param trabajoId La Clave compuesta de TrabajoTarea a actualizar.
     * @param tareaId La Clave compuesta de TrabajoTarea a actualizar.
     * @param trabajotareaDto El TrabajoTarea DTO con informacion actualizada.
     * @throws TrabajoTareaNoEncontradoException Si TrabajoTarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long trabajoId, Long tareaId, TrabajoTareaDto trabajotareaDto) throws TrabajoTareaNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() trabajotarea");

        try {
            TrabajoTareaDto trabajotareaDtoEncontrado = this.encontrarPorClave(trabajoId, tareaId); // Verifica si existe

            TrabajoTarea trabajotarea = mapper.toEntity(trabajotareaDto);
            int registrosActualizados = trabajotareaMapper.actualizar(trabajotarea);
            logeador.info("trabajotarea actualizado exitosamente: {}, registros actualizados: {}, {}", trabajotareaDto.getTrabajoId(), trabajotareaDto.getTareaId(), registrosActualizados);
        } catch (TrabajoTareaNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_ACTUALIZAR_EXECPTION + ": {}, {}, {}", trabajoId, tareaId, trabajotareaDto.toString(), e);
            throw new BaseDatosException(Constantes.TRABAJOTAREA_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina TrabajoTarea por Clave.
     * @param trabajoId La Clave compuesta de TrabajoTarea a eliminar.
     * @param tareaId La Clave compuesta de TrabajoTarea a eliminar.
     * @throws TrabajoTareaNoEncontradoException Si el TrabajoTarea no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long trabajoId, Long tareaId) throws TrabajoTareaNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() trabajotarea: {}, {}", trabajoId , tareaId);

        try {
            TrabajoTareaDto trabajotareaDto = this.encontrarPorClave(trabajoId,tareaId); // Verifica si existe
            int registrosEliminados = trabajotareaMapper.eliminar(trabajoId,tareaId);
            logeador.info("trabajotarea eliminado: {}, registros eliminados: {}, {}", trabajoId, tareaId, registrosEliminados);
        } catch (TrabajoTareaNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_ELIMINAR_EXECPTION + ": {}, {}", trabajoId, tareaId, e);
            throw new BaseDatosException(Constantes.TRABAJOTAREA_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un TrabajoTarea por Clave.
     * @param trabajoId La Clave compuesta TrabajoTarea a encontrar.
     * @param tareaId La Clave compuesta TrabajoTarea a encontrar.
     * @return El TrabajoTarea DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TrabajoTareaNoEncontradoException Si TrabajoTarea no es encontrado.
     */
    public TrabajoTareaDto encontrarPorClave(Long trabajoId, Long tareaId) throws BaseDatosException, TrabajoTareaNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}, {}", trabajoId, tareaId);

        try {
            TrabajoTareaDto trabajotareaDto = mapper.toDto(trabajotareaMapper.encontrarPorClave(trabajoId,tareaId));

            if (trabajotareaDto != null) {
                logeador.info("trabajotarea encontrado por clave : {}, {}", trabajoId, tareaId);
            } else {
                logeador.info("trabajotarea clave:{}, {} no encontrado", trabajoId, tareaId);
                throw new TrabajoTareaNoEncontradoException(Constantes.TRABAJOTAREA_NO_ENCONTRADO_MENSAGE);
            }

            return trabajotareaDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_ENCONTRAR_POR_CLAVE_EXECPTION + " {}, {}", trabajoId, tareaId, e);
            throw new BaseDatosException(Constantes.TRABAJOTAREA_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los TrabajoTareas.
     * @return Una lista de todos TrabajoTarea DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TrabajoTareaDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TrabajoTareaDto> trabajotareaList = mapper.toDtoList(trabajotareaMapper.obtenerTodos());
            logeador.info("trabajotareas obtenidos");
            return trabajotareaList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJOTAREA_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.TRABAJOTAREA_OBTENER_TODOS_EXECPTION, e);
        }
    }
}