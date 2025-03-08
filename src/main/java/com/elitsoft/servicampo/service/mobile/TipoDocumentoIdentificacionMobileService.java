package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.TipoDocumentoIdentificacionDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.TipoDocumentoIdentificacionMapper;
import com.elitsoft.servicampo.mapstruct.TipoDocumentoIdentificacionMapStruct;
import com.elitsoft.servicampo.service.core.TipoDocumentoIdentificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  TipoDocumentoIdentificacion.
 */
@Component
public class TipoDocumentoIdentificacionMobileService {

    @Autowired
    private TipoDocumentoIdentificacionMapper tipoDocumentoIdentificacionMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private TipoDocumentoIdentificacionService tipoDocumentoIdentificacionService; //Logica de Negocio del Core Service

    @Autowired
    private TipoDocumentoIdentificacionMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDTO())

    private static final Logger logeador = LoggerFactory.getLogger(TipoDocumentoIdentificacionMobileService.class); //Logback

    /**
     * Agrega un nuevo TipoDocumentoIdentificacion.
     * @param tipoDocumentoIdentificacionDTO el TipoDocumentoIdentificacion DTO.
     * @return el TipoDocumentoIdentificacion DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoDocumentoIdentificacion ya existe.
     */
    public TipoDocumentoIdentificacionDTO agregar(TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() tipodocumentoidentificacion");

        return tipoDocumentoIdentificacionService.agregar(tipoDocumentoIdentificacionDTO);
    }

    /**
     * Agrega Lote nuevos TipoDocumentoIdentificacion.
     * @param tipodocumentoIdentificacionLoteDTO lista de TipoDocumentoIdentificacion DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     * @throws RecursoDuplicadoException si el recurso TipoDocumentoIdentificacion ya existe.
     */
    public void agregarLote(List<TipoDocumentoIdentificacionDTO> tipodocumentoIdentificacionLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() tipodocumentoidentificacion");

        tipoDocumentoIdentificacionService.agregarLote(tipodocumentoIdentificacionLoteDTO);
    }

    /**
     * Actualiza un TipoDocumentoIdentificacion existente.
     * @param id la Clave de TipoDocumentoIdentificacion a actualizar.
     * @param tipoDocumentoIdentificacionDTO el TipoDocumentoIdentificacion DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDocumentoIdentificacion no es encontrado.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     */
    public void actualizar(Long id, TipoDocumentoIdentificacionDTO tipoDocumentoIdentificacionDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() tipodocumentoidentificacion");

        tipoDocumentoIdentificacionService.actualizar(id, tipoDocumentoIdentificacionDTO);
    }

    /**
     * Actualiza Lote de TipoDocumentoIdentificacion existentes.
     * @param tipoDocumentoIdentificacionLoteDTO lista de TipoDocumentoIdentificacion DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada TipoDocumentoIdentificacion tiene errores.
     */
    public void actualizarLote(List<TipoDocumentoIdentificacionDTO> tipoDocumentoIdentificacionLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() tipodocumentoidentificacion");

        tipoDocumentoIdentificacionService.actualizarLote(tipoDocumentoIdentificacionLoteDTO);
    }

    /**
     * Elimina TipoDocumentoIdentificacion por Clave.
     * @param id la clave de TipoDocumentoIdentificacion a eliminar.
     * @throws RecursoNoEncontradoException si el TipoDocumentoIdentificacion no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() tipodocumentoidentificacion: {}", id);
        tipoDocumentoIdentificacionService.eliminar(id);
    }

    /**
     * Elimina Lote TipoDocumentoIdentificacion por Clave.
     * @param idLote lista de claves de TipoDocumentoIdentificacion a eliminar.
     * @throws EntradaInvalidadException si la lista  TipoDocumentoIdentificacion esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        tipoDocumentoIdentificacionService.eliminarLote(idLote);
    }

    /**
     * Encuentra un TipoDocumentoIdentificacion por Clave.
     * @param id la clave TipoDocumentoIdentificacion a encontrar.
     * @return el TipoDocumentoIdentificacion DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si TipoDocumentoIdentificacion no es encontrado.
     */
    public TipoDocumentoIdentificacionDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return tipoDocumentoIdentificacionService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los TipoDocumentoIdentificacions.
     * @return lista de todos TipoDocumentoIdentificacion DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<TipoDocumentoIdentificacionDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return tipoDocumentoIdentificacionService.obtenerTodos();
    }
}