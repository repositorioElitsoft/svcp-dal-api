package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EmpleadoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EmpleadoNoEncontradoException;
import com.elitsoft.servicampo.mapper.EmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.EmpleadoMapStruct;
import com.elitsoft.servicampo.service.core.EmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Empleado.
 */
@Component
public class EmpleadoMobileService {

    @Autowired
    private EmpleadoMapper empleadoMapper;  //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EmpleadoService empleadoService; //Logica de Negocio del Core Service

    @Autowired
    private EmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(EmpleadoMobileService.class);

    /**
     * Agrega un nuevo Empleado.
     * @param empleadoDto El Empleado DTO.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void agregar(EmpleadoDto empleadoDto) throws BaseDatosException {
        logeador.debug("agregar() empleado");
        empleadoService.agregar(empleadoDto);
    }

    /**
     * Actualiza un Empleado existente.
     * @param id La Clave de Empleado a actualizar.
     * @param empleadoDto El Empleado DTO con informacion actualizada.
     * @throws EmpleadoNoEncontradoException Si Empleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void actualizar(Long id, EmpleadoDto empleadoDto) throws BaseDatosException, EmpleadoNoEncontradoException {
        logeador.debug("actualizar() empleado");
        empleadoService.actualizar(id, empleadoDto);
    }

    /**
     * Elimina Empleado por Clave.
     * @param id La Clave de Empleado a eliminar.
     * @throws EmpleadoNoEncontradoException Si el Empleado no es encontrado.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws BaseDatosException, EmpleadoNoEncontradoException {
        logeador.debug("eliminar() empleado: {}", id);
        empleadoService.eliminar(id);
    }

    /**
     * Encuentra un Empleado por Clave.
     * @param id La Clave Empleado a encontrar.
     * @return El Empleado DTO encontrado, o null si no es encontrado.
     * @throws BaseDatosException Si Ocurre un error de base de datos.
     * @throws EmpleadoNoEncontradoException Si Empleado no es encontrado.
     */
    public EmpleadoDto encontrarPorClave(Long id) throws BaseDatosException, EmpleadoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return empleadoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Empleados.
     * @return Una lista de todos Empleado DTOs.
     * @throws BaseDatosException Si ocurre un error de base de datos.
     */
    public List<EmpleadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return empleadoService.obtenerTodos();
    }
}