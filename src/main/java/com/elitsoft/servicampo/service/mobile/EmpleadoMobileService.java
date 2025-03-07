package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.EmpleadoDTO;
import com.elitsoft.servicampo.domain.entity.Empleado;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
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
    private EmpleadoMapper empleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private EmpleadoService empleadoService; //Logica de Negocio del Core Service

    @Autowired
    private EmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(EmpleadoMobileService.class); //Logback

    /**
     * Agrega un nuevo Empleado.
     * @param empleadoDTO el Empleado DTO.
     * @return el Empleado DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Empleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso Empleado ya existe.
     */
    public EmpleadoDTO agregar(EmpleadoDTO empleadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException, RecursoNoEncontradoException {
        logeador.debug("agregar() empleado");

        return empleadoService.agregar(empleadoDTO);
    }


    /**
     * Actualiza la clave de Empleado existente.
     * @param id la Clave de Empleado a actualizar.
     * @param empleadoDTO el Empleado DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Empleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada Empleado tiene errores.
     */
    public void actualizar(Long id, EmpleadoDTO empleadoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() empleado");

        empleadoService.actualizar(id, empleadoDTO);
    }

    /**
     * Actualiza un Empleado existente.
     * @param id la Clave de Empleado a actualizar.
     * @param contrasena La clave Empleado a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Empleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada Empleado tiene errores.
     */
    public void actualizarClave(Long id, String contrasena) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizarClave() empleado");

        empleadoService.actualizarClave(id, contrasena);
    }

    /**
     * Elimina Empleado por Clave.
     * @param id la clave de Empleado a eliminar.
     * @throws RecursoNoEncontradoException si el Empleado no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() empleado: {}", id);
        empleadoService.eliminar(id);
    }


    /**
     * Encuentra un Empleado por Clave.
     * @param id la clave Empleado a encontrar.
     * @return el Empleado DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Empleado no es encontrado.
     */
    public EmpleadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return empleadoService.encontrarPorClave(id);
    }

    /**
     * Encuentra un Empleado por correo.
     * @param email correo de Empleado a encontrar.
     * @return el Empleado encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     */
    public Empleado encontrarPorCorreo(String email) throws BaseDatosException {
        logeador.debug("encontrarPorCorreo(): {}", email);
        return empleadoService.encontrarPorCorreo(email);
    }

    /**
     * Obtiene todos los Empleados.
     * @return lista de todos Empleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<EmpleadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return empleadoService.obtenerTodos();
    }
}