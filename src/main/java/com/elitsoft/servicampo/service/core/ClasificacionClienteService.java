package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.ClasificacionClienteDto;
import com.elitsoft.servicampo.domain.entity.ClasificacionCliente;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.ClasificacionClienteMapper;
import com.elitsoft.servicampo.mapstruct.ClasificacionClienteMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad ClasificacionCliente.
 */
@Service
@Transactional
public class ClasificacionClienteService {

    @Autowired
    private ClasificacionClienteMapper clasificacionclienteMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private ClasificacionClienteMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(ClasificacionClienteService.class);

    /**
     * Agrega un nuevo ClasificacionCliente.
     * @param clasificacionclienteDto El ClasificacionCliente DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(ClasificacionClienteDto clasificacionclienteDto) throws BaseDatosException {
        logeador.debug("agregar() clasificacioncliente");


        try {
            ClasificacionCliente clasificacioncliente = mapper.toEntity(clasificacionclienteDto);
            Long nuevoId = clasificacionclienteMapper.agregar(clasificacioncliente);
            logeador.info("ClasificacionCliente agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_AGREGAR_EXECPTION + ": {}", clasificacionclienteDto.toString(), e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un ClasificacionCliente existente.
     * @param id La Clave de ClasificacionCliente a actualizar.
     * @param clasificacionclienteDto El ClasificacionCliente DTO con informacion actualizada.
     * @throws ClasificacionClienteNoEncontradoException Si ClasificacionCliente no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, ClasificacionClienteDto clasificacionclienteDto) throws ClasificacionClienteNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() clasificacioncliente");

        try {
            ClasificacionClienteDto clasificacionclienteDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            ClasificacionCliente clasificacioncliente = mapper.toEntity(clasificacionclienteDto);
            clasificacioncliente.setId(id);
            int registrosActualizados = clasificacionclienteMapper.actualizar(clasificacioncliente);
            logeador.info("clasificacioncliente actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (ClasificacionClienteNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ACTUALIZAR_EXECPTION + ": id={} {}", id, clasificacionclienteDto.toString(), e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina ClasificacionCliente por Clave.
     * @param id La Clave de ClasificacionCliente a eliminar.
     * @throws ClasificacionClienteNoEncontradoException Si el ClasificacionCliente no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws ClasificacionClienteNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() clasificacioncliente: {}", id);

        try {
            ClasificacionClienteDto clasificacionclienteDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = clasificacionclienteMapper.eliminar(id);
            logeador.info("clasificacioncliente eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (ClasificacionClienteNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un ClasificacionCliente por Clave.
     * @param id La Clave ClasificacionCliente a encontrar.
     * @return El ClasificacionCliente DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws ClasificacionClienteNoEncontradoException Si ClasificacionCliente no es encontrado.
     */
    public ClasificacionClienteDto encontrarPorClave(Long id) throws BaseDatosException, ClasificacionClienteNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            ClasificacionClienteDto clasificacionclienteDto = mapper.toDto(clasificacionclienteMapper.encontrarPorClave(id));

            if (clasificacionclienteDto != null) {
                logeador.info("clasificacioncliente encontrado por clave : {}", id);
            } else {
                logeador.info("clasificacioncliente clave:{} no encontrado", id);
                throw new ClasificacionClienteNoEncontradoException(Constantes.CLASIFICACIONCLIENTE_NO_ENCONTRADO_MENSAGE);
            }

            return clasificacionclienteDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los ClasificacionClientes.
     * @return Una lista de todos ClasificacionCliente DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<ClasificacionClienteDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<ClasificacionClienteDto> clasificacionclienteList = mapper.toDtoList(clasificacionclienteMapper.obtenerTodos());
            logeador.info("clasificacionclientes obtenidos");
            return clasificacionclienteList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.CLASIFICACIONCLIENTE_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.CLASIFICACIONCLIENTE_OBTENER_TODOS_EXECPTION, e);
        }
    }
}