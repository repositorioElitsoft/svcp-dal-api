package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoDireccionDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoDireccionMapper;
import com.elitsoft.servicampo.mapstruct.TipoDireccionMapStruct;
import com.elitsoft.servicampo.service.core.TipoDireccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoDireccion.
 */
@Component
public class TipoDireccionMobileService {

    @Autowired
    private TipoDireccionMapper tipodireccionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoDireccionService tipodireccionService; //Logica de Negocio del Core Service

    @Autowired
    private TipoDireccionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoDireccionMobileService.class); //Logback


    /**
     * Agrega un nuevo TipoDireccion.
     * @param tipodireccionDTO el TipoDireccion DTO.
     * @return el TipoDireccion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoDireccion ya existe.
     */
    public TipoDireccionDTO agregar(TipoDireccionDTO tipodireccionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipodireccion");

        return tipodireccionService.agregar(tipodireccionDTO);
    }

    /**
     * Agrega Lote nuevos TipoDireccion.
     * @param tipodireccionDTOLote lista de TipoDireccion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoDireccion ya existe.
     */
    public void agregarLote(List<TipoDireccionDTO> tipodireccionDTOLote) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipodireccion");

        tipodireccionService.agregarLote(tipodireccionDTOLote);
    }

    /**
     * Actualiza un TipoDireccion existente.
     * @param id la Clave de TipoDireccion a actualizar.
     * @param tipodireccionDTO el TipoDireccion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDireccion no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     */
    public void actualizar(Long id, TipoDireccionDTO tipodireccionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tipodireccion");

        tipodireccionService.actualizar(id, tipodireccionDTO);
    }

    /**
     * Actualiza Lote de TipoDireccion existentes.
     * @param tipodireccionDTOLote lista de TipoDireccion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDireccion tiene errores.
     */
    public void actualizarLote(List<TipoDireccionDTO> tipodireccionDTOLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipodireccion");

        tipodireccionService.actualizarLote(tipodireccionDTOLote);
    }

    /**
     * Elimina TipoDireccion por Clave.
     * @param id la clave de TipoDireccion a eliminar.
     * @throws RecursoNoEncontradoException si el TipoDireccion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipodireccion: {}", id);
        tipodireccionService.eliminar(id);
    }

    /**
     * Elimina Lote TipoDireccion por Clave.
     * @param idLote lista de claves de TipoDireccion a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoDireccion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipodireccionService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoDireccion por Clave.
     * @param id la clave TipoDireccion a encontrar.
     * @return el TipoDireccion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDireccion no es encontrado.
     */
    public TipoDireccionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipodireccionService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoDireccions.
     * @return lista de todos TipoDireccion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoDireccionDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipodireccionService.obtenerTodos();
    }
}