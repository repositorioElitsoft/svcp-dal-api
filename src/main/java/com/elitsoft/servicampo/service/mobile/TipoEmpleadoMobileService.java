package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoEmpleadoMapper;
import com.elitsoft.servicampo.mapstruct.TipoEmpleadoMapStruct;
import com.elitsoft.servicampo.service.core.TipoEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoEmpleado.
 */
@Component
public class TipoEmpleadoMobileService {

    @Autowired
    private TipoEmpleadoMapper tipoempleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoEmpleadoService tipoempleadoService; //Logica de Negocio del Core Service

    @Autowired
    private TipoEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoMobileService.class); //Logback

    /**
     * Agrega un nuevo TipoEmpleado.
     * @param tipoempleadoDto el TipoEmpleado DTO.
     * @return el TipoEmpleado DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoEmpleado ya existe.
     */
    public TipoEmpleadoDto agregar(TipoEmpleadoDto tipoempleadoDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipoempleado");

        return tipoempleadoService.agregar(tipoempleadoDto);
    }

    /**
     * Agrega Lote nuevos TipoEmpleado.
     * @param tipoempleadoLoteDto lista de TipoEmpleado DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoEmpleado ya existe.
     */
    public void agregarLote(List<TipoEmpleadoDto> tipoempleadoLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoempleado");

        tipoempleadoService.agregarLote(tipoempleadoLoteDto);
    }

    /**
     * Actualiza un TipoEmpleado existente.
     * @param id la Clave de TipoEmpleado a actualizar.
     * @param tipoempleadoDto el TipoEmpleado DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     */
    public void actualizar(Long id, TipoEmpleadoDto tipoempleadoDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tipoempleado");

        tipoempleadoService.actualizar(id, tipoempleadoDto);
    }

    /**
     * Actualiza Lote de TipoEmpleado existentes.
     * @param tipoempleadoLoteDto lista de TipoEmpleado DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     */
    public void actualizarLote(List<TipoEmpleadoDto> tipoempleadoLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoempleado");

        tipoempleadoService.actualizarLote(tipoempleadoLoteDto);
    }

    /**
     * Elimina TipoEmpleado por Clave.
     * @param id la clave de TipoEmpleado a eliminar.
     * @throws RecursoNoEncontradoException si el TipoEmpleado no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoempleado: {}", id);
        tipoempleadoService.eliminar(id);
    }

    /**
     * Elimina Lote TipoEmpleado por Clave.
     * @param idLote lista de claves de TipoEmpleado a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoEmpleado esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipoempleadoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoEmpleado por Clave.
     * @param id la clave TipoEmpleado a encontrar.
     * @return el TipoEmpleado DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     */
    public TipoEmpleadoDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoempleadoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoEmpleados.
     * @return lista de todos TipoEmpleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoEmpleadoDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoempleadoService.obtenerTodos();
    }
}