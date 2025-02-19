package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.EstadoDto;
import com.elitsoft.servicampo.domain.entity.Estado;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.EstadoMapper;
import com.elitsoft.servicampo.mapstruct.EstadoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Estado.
 */
@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoMapper estadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EstadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EstadoService.class);

    /**
     * Agrega un nuevo Estado.
     * @param estadoDto El Estado DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(EstadoDto estadoDto) throws BaseDatosException {
        logeador.debug("agregar() estado");


        try {
            Estado estado = mapper.toEntity(estadoDto);
            Long nuevoId = estadoMapper.agregar(estado);
            logeador.info("Estado agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_AGREGAR_EXECPTION + ": {}", estadoDto.toString(), e);
            throw new BaseDatosException(Constantes.ESTADO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Estado existente.
     * @param id La Clave de Estado a actualizar.
     * @param estadoDto El Estado DTO con informacion actualizada.
     * @throws EstadoNoEncontradoException Si Estado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, EstadoDto estadoDto) throws EstadoNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() estado");

        try {
            EstadoDto estadoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Estado estado = mapper.toEntity(estadoDto);
            estado.setId(id);
            int registrosActualizados = estadoMapper.actualizar(estado);
            logeador.info("estado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (EstadoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_ACTUALIZAR_EXECPTION + ": id={} {}", id, estadoDto.toString(), e);
            throw new BaseDatosException(Constantes.ESTADO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Estado por Clave.
     * @param id La Clave de Estado a eliminar.
     * @throws EstadoNoEncontradoException Si el Estado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws EstadoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() estado: {}", id);

        try {
            EstadoDto estadoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = estadoMapper.eliminar(id);
            logeador.info("estado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (EstadoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.ESTADO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Estado por Clave.
     * @param id La Clave Estado a encontrar.
     * @return El Estado DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws EstadoNoEncontradoException Si Estado no es encontrado.
     */
    public EstadoDto encontrarPorClave(Long id) throws BaseDatosException, EstadoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            EstadoDto estadoDto = mapper.toDto(estadoMapper.encontrarPorClave(id));

            if (estadoDto != null) {
                logeador.info("estado encontrado por clave : {}", id);
            } else {
                logeador.info("estado clave:{} no encontrado", id);
                throw new EstadoNoEncontradoException(Constantes.ESTADO_NO_ENCONTRADO_MENSAGE);
            }

            return estadoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.ESTADO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Estados.
     * @return Una lista de todos Estado DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<EstadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<EstadoDto> estadoList = mapper.toDtoList(estadoMapper.obtenerTodos());
            logeador.info("estados obtenidos");
            return estadoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.ESTADO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.ESTADO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}