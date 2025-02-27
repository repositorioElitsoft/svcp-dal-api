package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoServicioDto;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoServicioMapper;
import com.elitsoft.servicampo.mapstruct.TipoServicioMapStruct;
import com.elitsoft.servicampo.service.core.TipoServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoServicio.
 */
@Component
public class TipoServicioMobileService {

    @Autowired
    private TipoServicioMapper tiposervicioMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoServicioService tiposervicioService; //Logica de Negocio del Core Service

    @Autowired
    private TipoServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoServicioMobileService.class); //Logback


    /**
     * Agrega un nuevo TipoServicio.
     * @param tiposervicioDto el TipoServicio DTO.
     * @return el TipoServicio DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoServicio ya existe.
     */
    public TipoServicioDto agregar(TipoServicioDto tiposervicioDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tiposervicio");

        return tiposervicioService.agregar(tiposervicioDto);
    }

    /**
     * Agrega Lote nuevos TipoServicio.
     * @param tiposervicioLoteDto lista de TipoServicio DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoServicio ya existe.
     */
    public void agregarLote(List<TipoServicioDto> tiposervicioLoteDto) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tiposervicio");

        tiposervicioService.agregarLote(tiposervicioLoteDto);
    }

    /**
     * Actualiza un TipoServicio existente.
     * @param id la Clave de TipoServicio a actualizar.
     * @param tiposervicioDto el TipoServicio DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     */
    public void actualizar(Long id, TipoServicioDto tiposervicioDto) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tiposervicio");

        tiposervicioService.actualizar(id, tiposervicioDto);
    }

    /**
     * Actualiza Lote de TipoServicio existentes.
     * @param tiposervicioLoteDto lista de TipoServicio DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     */
    public void actualizarLote(List<TipoServicioDto> tiposervicioLoteDto) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tiposervicio");

        tiposervicioService.actualizarLote(tiposervicioLoteDto);
    }

    /**
     * Elimina TipoServicio por Clave.
     * @param id la clave de TipoServicio a eliminar.
     * @throws RecursoNoEncontradoException si el TipoServicio no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tiposervicio: {}", id);
        tiposervicioService.eliminar(id);
    }

    /**
     * Elimina Lote TipoServicio por Clave.
     * @param idLote lista de claves de TipoServicio a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoServicio esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tiposervicioService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoServicio por Clave.
     * @param id la clave TipoServicio a encontrar.
     * @return el TipoServicio DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     */
    public TipoServicioDto encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tiposervicioService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoServicios.
     * @return lista de todos TipoServicio DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoServicioDto> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tiposervicioService.obtenerTodos();
    }
}