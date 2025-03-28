package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoEmpleadoDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoEliminarException;
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
    private TipoEmpleadoMapper tipoEmpleadoMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoEmpleadoService tipoEmpleadoService; //Logica de Negocio del Core Service

    @Autowired
    private TipoEmpleadoMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoEmpleadoMobileService.class); //Logback

    /**
     * Agrega un nuevo TipoEmpleado.
     *
     * @param tipoEmpleadoDTO el TipoEmpleado DTO.
     * @return el TipoEmpleado DTO agregado con campo auto generado.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoEmpleado ya existe.
     */
    public TipoEmpleadoDTO agregar(TipoEmpleadoDTO tipoEmpleadoDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipoempleado");

        return tipoEmpleadoService.agregar(tipoEmpleadoDTO);
    }

    /**
     * Agrega Lote nuevos TipoEmpleado.
     *
     * @param tipoEmpleadoLoteDTO lista de TipoEmpleado DTO a agregar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoEmpleado ya existe.
     */
    public void agregarLote(List<TipoEmpleadoDTO> tipoEmpleadoLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipoempleado");

        tipoEmpleadoService.agregarLote(tipoEmpleadoLoteDTO);
    }

    /**
     * Actualiza un TipoEmpleado existente.
     *
     * @param id              la Clave de TipoEmpleado a actualizar.
     * @param tipoEmpleadoDTO el TipoEmpleado DTO con informacion actualizada.
     * @throws BaseDatosException           si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     * @throws EntradaInvalidadException    si la entrada TipoEmpleado tiene errores.
     */
    public void actualizar(Long id, TipoEmpleadoDTO tipoEmpleadoDTO) throws BaseDatosException, RecursoNoEncontradoException, EntradaInvalidadException {
        logeador.debug("actualizar() tipoempleado");

        tipoEmpleadoService.actualizar(id, tipoEmpleadoDTO);
    }

    /**
     * Actualiza Lote de TipoEmpleado existentes.
     *
     * @param tipoEmpleadoLoteDTO lista de TipoEmpleado DTO con datos a actualizar.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoEmpleado tiene errores.
     */
    public void actualizarLote(List<TipoEmpleadoDTO> tipoEmpleadoLoteDTO) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipoempleado");

        tipoEmpleadoService.actualizarLote(tipoEmpleadoLoteDTO);
    }

    /**
     * Elimina TipoEmpleado por Clave.
     *
     * @param id la clave de TipoEmpleado a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoEmpleado esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TipoEmpleado esta asociado a otro recurso
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipoempleado: {}", id);
        tipoEmpleadoService.eliminar(id);
    }

    /**
     * Elimina Lote TipoEmpleado por Clave.
     *
     * @param idLote lista de claves de TipoEmpleado a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoEmpleado esta vacia.
     * @throws BaseDatosException        si ocurre un error de base de datos.
     * @throws RecursoEliminarException  si TipoEmpleado esta asociado a otro recurso
     */
    public void eliminarLote(List<Long> idLote) throws BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipoEmpleadoService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoEmpleado por Clave.
     *
     * @param id la clave TipoEmpleado a encontrar.
     * @return el TipoEmpleado DTO encontrado.
     * @throws BaseDatosException           si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoEmpleado no es encontrado.
     */
    public TipoEmpleadoDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoEmpleadoService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoEmpleados.
     *
     * @return lista de todos TipoEmpleado DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoEmpleadoDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoEmpleadoService.obtenerTodos();
    }
}