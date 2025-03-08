package com.elitsoft.servicampo.service.mobile;

import com.elitsoft.servicampo.domain.dto.core.PaisDTO;
import com.elitsoft.servicampo.exceptions.BaseDatosException;
import com.elitsoft.servicampo.exceptions.EntradaInvalidadException;
import com.elitsoft.servicampo.exceptions.RecursoDuplicadoException;
import com.elitsoft.servicampo.exceptions.RecursoNoEncontradoException;
import com.elitsoft.servicampo.mapper.PaisMapper;
import com.elitsoft.servicampo.mapstruct.PaisMapStruct;
import com.elitsoft.servicampo.service.core.PaisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase de Servicio Movil para la entidad  Pais.
 */
@Component
public class PaisMobileService {

    @Autowired
    private PaisMapper paisMapper; //Acceso a la base de datos con MyBatis, actua como un repositorio

    @Autowired
    private PaisService paisService; //Logica de Negocio del Core Service

    @Autowired
    private PaisMapStruct mapper; // MapStruct Mapper (ToEntity(), ToDto())

    private static final Logger logeador = LoggerFactory.getLogger(PaisMobileService.class); //Logback

    /**
     * Agrega un nuevo Pais.
     * @param paisDTO el Pais DTO.
     * @return el Pais DTO agregado con campo auto generado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     * @throws RecursoDuplicadoException si el recurso Pais ya existe.
     */
    public PaisDTO agregar(PaisDTO paisDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregar() pais");

        return paisService.agregar(paisDTO);
    }

    /**
     * Agrega Lote nuevos Pais.
     * @param paisLoteDTO lista de Pais DTO a agregar.
     * @throws BaseDatosException  si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     * @throws RecursoDuplicadoException si el recurso Pais ya existe.
     */
    public void agregarLote(List<PaisDTO> paisLoteDTO) throws BaseDatosException, EntradaInvalidadException, RecursoDuplicadoException {
        logeador.debug("agregarLote() pais");

        paisService.agregarLote(paisLoteDTO);
    }

    /**
     * Actualiza un Pais existente.
     * @param id la Clave de Pais a actualizar.
     * @param paisDTO el Pais DTO con informacion actualizada.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Pais no es encontrado.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     */
    public void actualizar(Long id, PaisDTO paisDTO) throws BaseDatosException, RecursoNoEncontradoException , EntradaInvalidadException  {
        logeador.debug("actualizar() pais");

        paisService.actualizar(id, paisDTO);
    }

    /**
     * Actualiza Lote de Pais existentes.
     * @param paisLoteDTO lista de Pais DTO con datos a actualizar.
     * @throws BaseDatosException si ocurre un error de base de datos.
     * @throws EntradaInvalidadException si la entrada Pais tiene errores.
     */
    public void actualizarLote(List<PaisDTO> paisLoteDTO) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("actualizarLote() pais");

        paisService.actualizarLote(paisLoteDTO);
    }

    /**
     * Elimina Pais por Clave.
     * @param id la clave de Pais a eliminar.
     * @throws RecursoNoEncontradoException si el Pais no es encontrado.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminar(Long id) throws RecursoNoEncontradoException, BaseDatosException {
        logeador.debug("eliminar() pais: {}", id);
        paisService.eliminar(id);
    }

    /**
     * Elimina Lote Pais por Clave.
     * @param idLote lista de claves de Pais a eliminar.
     * @throws EntradaInvalidadException si la lista  Pais esta vacia.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public void eliminarLote(List<Long> idLote) throws  BaseDatosException, EntradaInvalidadException {
        logeador.debug("eliminarLote()");

        paisService.eliminarLote(idLote);
    }

    /**
     * Encuentra un Pais por Clave.
     * @param id la clave Pais a encontrar.
     * @return el Pais DTO encontrado.
     * @throws BaseDatosException si Ocurre un error de base de datos.
     * @throws RecursoNoEncontradoException si Pais no es encontrado.
     */
    public PaisDTO encontrarPorClave(Long id) throws BaseDatosException, RecursoNoEncontradoException {
        logeador.debug("encontrarPorClave(): {}", id);
        return paisService.encontrarPorClave(id);
    }

    /**
     * Obtiene todos los Paiss.
     * @return lista de todos Pais DTOs.
     * @throws BaseDatosException si ocurre un error de base de datos.
     */
    public List<PaisDTO> obtenerTodos() throws BaseDatosException {
        logeador.debug("obtenerTodos()");
        return paisService.obtenerTodos();
    }
}