package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.domain.entity.TipoEmpleado;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.TipoEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.TipoEmpleadoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad TipoEmpleado.
 */
@Service
@Transactional
public class TipoEmpleadoService {

    @Autowired
    private TipoEmpleadoMapper tipoempleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoService.class);

    /**
     * Agrega un nuevo TipoEmpleado.
     * @param tipoempleadoDto El TipoEmpleado DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(TipoEmpleadoDto tipoempleadoDto) throws BaseDatosException {
        logeador.debug("agregar() tipoempleado");


        try {
            TipoEmpleado tipoempleado = mapper.toEntity(tipoempleadoDto);
            Long nuevoId = tipoempleadoMapper.agregar(tipoempleado);
            logeador.info("TipoEmpleado agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_AGREGAR_EXECPTION + ": {}", tipoempleadoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un TipoEmpleado existente.
     * @param id La Clave de TipoEmpleado a actualizar.
     * @param tipoempleadoDto El TipoEmpleado DTO con informacion actualizada.
     * @throws TipoEmpleadoNoEncontradoException Si TipoEmpleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, TipoEmpleadoDto tipoempleadoDto) throws TipoEmpleadoNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() tipoempleado");

        try {
            TipoEmpleadoDto tipoempleadoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            TipoEmpleado tipoempleado = mapper.toEntity(tipoempleadoDto);
            tipoempleado.setId(id);
            int registrosActualizados = tipoempleadoMapper.actualizar(tipoempleado);
            logeador.info("tipoempleado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (TipoEmpleadoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ACTUALIZAR_EXECPTION + ": id={} {}", id, tipoempleadoDto.toString(), e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina TipoEmpleado por Clave.
     * @param id La Clave de TipoEmpleado a eliminar.
     * @throws TipoEmpleadoNoEncontradoException Si el TipoEmpleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws TipoEmpleadoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoempleado: {}", id);

        try {
            TipoEmpleadoDto tipoempleadoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = tipoempleadoMapper.eliminar(id);
            logeador.info("tipoempleado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (TipoEmpleadoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un TipoEmpleado por Clave.
     * @param id La Clave TipoEmpleado a encontrar.
     * @return El TipoEmpleado DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws TipoEmpleadoNoEncontradoException Si TipoEmpleado no es encontrado.
     */
    public TipoEmpleadoDto encontrarPorClave(Long id) throws BaseDatosException, TipoEmpleadoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            TipoEmpleadoDto tipoempleadoDto = mapper.toDto(tipoempleadoMapper.encontrarPorClave(id));

            if (tipoempleadoDto != null) {
                logeador.info("tipoempleado encontrado por clave : {}", id);
            } else {
                logeador.info("tipoempleado clave:{} no encontrado", id);
                throw new TipoEmpleadoNoEncontradoException(Constantes.TIPOEMPLEADO_NO_ENCONTRADO_MENSAGE);
            }

            return tipoempleadoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los TipoEmpleados.
     * @return Una lista de todos TipoEmpleado DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<TipoEmpleadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<TipoEmpleadoDto> tipoempleadoList = mapper.toDtoList(tipoempleadoMapper.obtenerTodos());
            logeador.info("tipoempleados obtenidos");
            return tipoempleadoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.TIPOEMPLEADO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.TIPOEMPLEADO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}