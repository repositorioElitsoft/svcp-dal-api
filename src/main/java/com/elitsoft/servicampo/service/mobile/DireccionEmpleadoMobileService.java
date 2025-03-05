package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.DireccionEmpleadoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.DireccionEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.DireccionEmpleadoMapStruct;
import com.elitsoft.servicampo.service.core.DireccionEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  DireccionEmpleado.
 */
@Component
public class DireccionEmpleadoMobileService {

    @Autowired
    private DireccionEmpleadoMapper direccionempleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private DireccionEmpleadoService direccionempleadoService; //Logica de Negocio del Core Service

    @Autowired
    private DireccionEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(DireccionEmpleadoMobileService.class); //Logback


    /**
     * Agrega un nuevo DireccionEmpleado.
     * @param direccionempleadoDTO el DireccionEmpleado DTO.
     * @return el DireccionEmpleado DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso DireccionEmpleado ya existe.
     */
    public DireccionEmpleadoDTO agregar(DireccionEmpleadoDTO direccionempleadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() direccionempleado");

        return direccionempleadoService.agregar(direccionempleadoDTO);
    }

    /**
     * Agrega Lote nuevos DireccionEmpleado.
     * @param direccionempleadoLoteDTO lista de DireccionEmpleado DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso DireccionEmpleado ya existe.
     */
    public void agregarLote(List<DireccionEmpleadoDTO> direccionempleadoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() direccionempleado");

        direccionempleadoService.agregarLote(direccionempleadoLoteDTO);
    }

    /**
     * Actualiza un DireccionEmpleado existente.
     * @param id la Clave de DireccionEmpleado a actualizar.
     * @param direccionempleadoDTO el DireccionEmpleado DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DireccionEmpleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     */
    public void actualizar(Long id, DireccionEmpleadoDTO direccionempleadoDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() direccionempleado");

        direccionempleadoService.actualizar(id, direccionempleadoDTO);
    }

    /**
     * Actualiza Lote de DireccionEmpleado existentes.
     * @param direccionempleadoLoteDTO lista de DireccionEmpleado DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada DireccionEmpleado tiene errores.
     */
    public void actualizarLote(List<DireccionEmpleadoDTO> direccionempleadoLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() direccionempleado");

        direccionempleadoService.actualizarLote(direccionempleadoLoteDTO);
    }

    /**
     * Elimina DireccionEmpleado por Clave.
     * @param id la clave de DireccionEmpleado a eliminar.
     * @throws RecursoNoEncontradoException si el DireccionEmpleado no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() direccionempleado: {}", id);
        direccionempleadoService.eliminar(id);
    }

    /**
     * Elimina Lote DireccionEmpleado por Clave.
     * @param idLote lista de claves de DireccionEmpleado a eliminar.
     * @throws EntradaInvalidadException si la lista  DireccionEmpleado esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        direccionempleadoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un DireccionEmpleado por Clave.
     * @param id la clave DireccionEmpleado a encontrar.
     * @return el DireccionEmpleado DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si DireccionEmpleado no es encontrado.
     */
    public DireccionEmpleadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return direccionempleadoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los DireccionEmpleados.
     * @return lista de todos DireccionEmpleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<DireccionEmpleadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return direccionempleadoService.obtenerTodos();
    }
}