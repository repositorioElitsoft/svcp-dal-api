package com.elitsoft.servicampo.service.core;

import com.elitsoft.servicampo.domain.dto.core.EmpleadoDto;
import com.elitsoft.servicampo.domain.entity.Empleado;
import com.elitsoft.servicampo.exceptions.*;
import com.elitsoft.servicampo.mapper.EmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.EmpleadoMapStruct;
import com.elitsoft.servicampo.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de Servicio para la entidad Empleado.
 */
@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoMapper empleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EmpleadoService.class);

    /**
     * Agrega un nuevo Empleado.
     * @param empleadoDto El Empleado DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(EmpleadoDto empleadoDto) throws BaseDatosException {
        logeador.debug("agregar() empleado");


        try {
            Empleado empleado = mapper.toEntity(empleadoDto);
            Long nuevoId = empleadoMapper.agregar(empleado);
            logeador.info("Empleado agregado exitosamente id: {}", nuevoId);
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_AGREGAR_EXECPTION + ": {}", empleadoDto.toString(), e);
            throw new BaseDatosException(Constantes.EMPLEADO_AGREGAR_EXECPTION, e);
        }
    }

    /**
     * Actualiza un Empleado existente.
     * @param id La Clave de Empleado a actualizar.
     * @param empleadoDto El Empleado DTO con informacion actualizada.
     * @throws EmpleadoNoEncontradoException Si Empleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, EmpleadoDto empleadoDto) throws EmpleadoNoEncontradoException, BaseDatosException {
        logeador.debug("actualizar() empleado");

        try {
            EmpleadoDto empleadoDtoEncontrado = this.encontrarPorClave(id); // Verifica si existe

            Empleado empleado = mapper.toEntity(empleadoDto);
            empleado.setId(id);
            int registrosActualizados = empleadoMapper.actualizar(empleado);
            logeador.info("empleado actualizado exitosamente: {}, registros actualizados: {}", id, registrosActualizados);
        } catch (EmpleadoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_ACTUALIZAR_EXECPTION + ": id={} {}", id, empleadoDto.toString(), e);
            throw new BaseDatosException(Constantes.EMPLEADO_ACTUALIZAR_EXECPTION, e);
        }
    }

    /**
     * Elimina Empleado por Clave.
     * @param id La Clave de Empleado a eliminar.
     * @throws EmpleadoNoEncontradoException Si el Empleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws EmpleadoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() empleado: {}", id);

        try {
            EmpleadoDto empleadoDto = this.encontrarPorClave(id); // Verifica si existe
            int registrosEliminados = empleadoMapper.eliminar(id);
            logeador.info("empleado eliminado: {}, registros eliminados: {}", id, registrosEliminados);
        } catch (EmpleadoNoEncontradoException e) {
            throw e;
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_ELIMINAR_EXECPTION + ": {}", id, e);
            throw new BaseDatosException(Constantes.EMPLEADO_ELIMINAR_EXECPTION, e);
        }

    }

    /**
     * Encuentra un Empleado por Clave.
     * @param id La Clave Empleado a encontrar.
     * @return El Empleado DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws EmpleadoNoEncontradoException Si Empleado no es encontrado.
     */
    public EmpleadoDto encontrarPorClave(Long id) throws BaseDatosException, EmpleadoNoEncontradoException {
        logeador.debug("obtenerPorClave(): {}", id);

        try {
            EmpleadoDto empleadoDto = mapper.toDto(empleadoMapper.encontrarPorClave(id));

            if (empleadoDto != null) {
                logeador.info("empleado encontrado por clave : {}", id);
            } else {
                logeador.info("empleado clave:{} no encontrado", id);
                throw new EmpleadoNoEncontradoException(Constantes.EMPLEADO_NO_ENCONTRADO_MENSAGE);
            }

            return empleadoDto;
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_ENCONTRAR_POR_CLAVE_EXECPTION + " {}", id, e);
            throw new BaseDatosException(Constantes.EMPLEADO_ENCONTRAR_POR_CLAVE_EXECPTION, e);
        }
    }

    /**
     * Obtiene todos los Empleados.
     * @return Una lista de todos Empleado DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<EmpleadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");

        try {
            List<EmpleadoDto> empleadoList = mapper.toDtoList(empleadoMapper.obtenerTodos());
            logeador.info("empleados obtenidos");
            return empleadoList;
        } catch (DataAccessException e) {
            logeador.error(Constantes.EMPLEADO_OBTENER_TODOS_EXECPTION, e);
            throw new BaseDatosException(Constantes.EMPLEADO_OBTENER_TODOS_EXECPTION, e);
        }
    }
}