package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoServicioDTO;
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
    private TipoServicioMapper tipoServicioMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoServicioService tipoServicioService; //Logica de Negocio del Core Service

    @Autowired
    private TipoServicioMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(TipoServicioMobileService.class); //Logback


    /**
     * Agrega un nuevo TipoServicio.
     * @param tipoServicioDTO el TipoServicio DTO.
     * @return el TipoServicio DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoServicio ya existe.
     */
    public TipoServicioDTO agregar(TipoServicioDTO tipoServicioDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tiposervicio");

        return tipoServicioService.agregar(tipoServicioDTO);
    }

    /**
     * Agrega Lote nuevos TipoServicio.
     * @param tipoServicioLoteDTO lista de TipoServicio DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoServicio ya existe.
     */
    public void agregarLote(List<TipoServicioDTO> tipoServicioLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tiposervicio");

        tipoServicioService.agregarLote(tipoServicioLoteDTO);
    }

    /**
     * Actualiza un TipoServicio existente.
     * @param id la Clave de TipoServicio a actualizar.
     * @param tipoServicioDTO el TipoServicio DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     */
    public void actualizar(Long id, TipoServicioDTO tipoServicioDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tiposervicio");

        tipoServicioService.actualizar(id, tipoServicioDTO);
    }

    /**
     * Actualiza Lote de TipoServicio existentes.
     * @param tipoServicioLoteDTO lista de TipoServicio DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoServicio tiene errores.
     */
    public void actualizarLote(List<TipoServicioDTO> tipoServicioLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tiposervicio");

        tipoServicioService.actualizarLote(tipoServicioLoteDTO);
    }

    /**
     * Elimina TipoServicio por Clave.
     * @param id la clave de TipoServicio a eliminar.
     * @throws RecursoNoEncontradoException si el TipoServicio no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tiposervicio: {}", id);
        tipoServicioService.eliminar(id);
    }

    /**
     * Elimina Lote TipoServicio por Clave.
     * @param idLote lista de claves de TipoServicio a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoServicio esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipoServicioService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoServicio por Clave.
     * @param id la clave TipoServicio a encontrar.
     * @return el TipoServicio DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoServicio no es encontrado.
     */
    public TipoServicioDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoServicioService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoServicios.
     * @return lista de todos TipoServicio DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoServicioDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoServicioService.obtenerTodos();
    }
}