package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TrabajoDto;
import com.elitsoft.servicampo.domain.entity.Trabajo;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TrabajoMapper;
import com.elitsoft.servicampo.mapstruct.TrabajoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Trabajo.
 */
@Service
@Transactional
public class TrabajoService {

    @Autowired
    private TrabajoMapper trabajoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TrabajoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TrabajoService.class);

    /**
     * Agrega un nuevo Trabajo.
     * @param trabajoDto El Trabajo DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TrabajoDto trabajoDto) throws BaseDatosException {
        logeador.debug("agregar() trabajo");


        try {
            Trabajo trabajo = mapper.toEntity(trabajoDto);
            Long nuevoId = trabajoMapper.agregar(trabajo);
            logeador.info("Trabajo agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_AGREGAR_EXECPTION + ": {}", trabajoDto.toString(), e);
            throw new BaseDatosException(Constantes.TRABAJO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Trabajo existente.
     * @param id La Clave de Trabajo a actualizar.
     * @param trabajoDto El Trabajo DTO con informacion actualizada.
     * @throws TrabajoNoEncontradoException Si Trabajo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TrabajoDto trabajoDto) throws TrabajoNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() trabajo");

        try {
            TrabajoDto trabajoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Trabajo trabajo = mapper.toEntity(trabajoDto);
            trabajo.setId(id);
            int registrosActualizados = trabajoMapper.actualizar(trabajo);
            logeador.info("trabajo actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (TrabajoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_ACTUALIZAR_EXECPTION + ": id={} {}", id, trabajoDto.toString(), e);
            throw new BaseDatosException(Constantes.TRABAJO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Trabajo por Clave.
     * @param id La Clave de Trabajo a eliminar.
     * @throws TrabajoNoEncontradoException Si el Trabajo no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws TrabajoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() trabajo: {}", id);

        try {
            TrabajoDto trabajoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = trabajoMapper.eliminar(id);
            logeador.info("trabajo eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (TrabajoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.TRABAJO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Trabajo por Clave.
     * @param id La Clave Trabajo a encontrar.
     * @return El Trabajo DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TrabajoNoEncontradoException Si Trabajo no es encontrado.
     */
    public TrabajoDto encontrarPorClave(Long id) throws BaseDatosException, TrabajoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TrabajoDto trabajoDto = mapper.toDto(trabajoMapper.encontrarPorClave(id));

            if (trabajoDto != null) {
                logeador.info("trabajo encontrado por clave : {}", id);
            } else {
                logeador.info("trabajo clave:{} no encontrado", id);
                throw new TrabajoNoEncontradoException(Constantes.TRABAJO_NO_ENCONTRADO_MENSAGE);
            }

            return trabajoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.TRABAJO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Trabajos.
     * @return Una lista de todos Trabajo DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TrabajoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TrabajoDto> trabajoList = mapper.toDtoList(trabajoMapper.obtenerTodos());
            logeador.info("trabajos obtenidos");
            return trabajoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TRABAJO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.TRABAJO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}